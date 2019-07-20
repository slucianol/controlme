pipeline{
	agent any
	environment {
		WORKING_BRANCH = "${env.BRANCH_NAME.split('/')[1]}"
		SLOT_CREATED = false
	}
	stages{
		stage("code_analysis"){
			steps{
				script{
					echo "${WORKING_BRANCH}"
					sh 'sonar-scanner'
				}
			}
		}
		stage("build"){
			steps{
				script{
					dir("${env.WORKSPACE}/ControlMe.WebApi"){
						sh 'dotnet clean'
						sh 'dotnet restore'
						sh 'dotnet publish --configuration Release --output ../artifacts --no-restore'
					}
				}
			}
		}
		stage("unit_test"){
			steps{
				script{
					dir("${env.WORKSPACE}/ControlMe.Tests"){
						sh 'dotnet test --configuration Release'
					}
				}
			}
		}
		stage("deployment"){
			environment{
				AZURE_SERVICE_PRINCIPAL = credentials('AZURE_SERVICE_PRINCIPAL')
				AZURE_SERVICE_PRINCIPAL_SECRET = credentials('AZURE_SERVICE_PRINCIPAL_SECRET')
				AZURE_SERVICE_PRINCIPAL_TENANT = credentials('AZURE_SERVICE_PRINCIPAL_TENANT')
			}
			steps{
				script{
					dir("${env.WORKSPACE}/artifacts"){
						
						sh 'zip ControlMe.zip *'
						sh "az login --service-principal -u ${AZURE_SERVICE_PRINCIPAL} -p ${AZURE_SERVICE_PRINCIPAL_SECRET} --tenant ${AZURE_SERVICE_PRINCIPAL_TENANT}"
						sh "az webapp deployment slot create --resource-group RG_DEVOPS_DAY --name api-controlme --slot ${WORKING_BRANCH}"
						SLOT_CREATED = true
						sh "az webapp deployment source config-zip --resource-group RG_DEVOPS_DAY --name api-controlme --src ControlMe.zip --slot ${WORKING_BRANCH}"
					}
				}
			}
		}
		stage("tests"){
			steps{
				parallel load:{
					sh "curl https://api-controlme-${WORKING_BRANCH}.azurewebsites.net/api/Incomes --insecure"
				},
				functional:{
					dir("${WORKSPACE}/functional_tests/ControMe"){
						sh "/usr/lib/katalon/./katalon -runMode=console -noSplash -projectPath=\"ControlMe.prj\" -testSuitePath=\"Test Suites/Verify Incomes Endpoint\" -executionProfile=\"default\" -browserType=\"Web Service\" -g_TestUrl=\"https://api-controlme-${WORKING_BRANCH}.azurewebsites.net\""
					}
				},
				failFast: true
			}
		}
	}
	post{
		always{
			script{
				if(SLOT_CREATED == true){
					sh "az webapp deployment slot delete --resource-group RG_DEVOPS_DAY --name api-controlme --slot ${WORKING_BRANCH}"
				}
			}
		}
	}
}
