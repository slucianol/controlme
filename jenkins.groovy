pipeline{
	agent any
	enviroment{
		WORKING_BRANCH = env.BRANCH_NAME.split('/')[1]
		SLOT_CREATED = false
	}
	stages{
		stage("code_analysis"){
			steps{
				script{
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
			steps{
				script{
					dir("${env.WORKSPACE}/artifacts"){
						sh 'zip ControlMe.zip *'
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
					echo 'Executing load testing...'
				},
				functional:{
					echo 'Executing functional testing...'
				},
				failFast: true
			}
		}
	}
	post{
		always{
			if(SLOT_CREATED){
				echo 'Remove created slot...'
				//sh "az webapp deployment slot delete --resource-group RG_DEVOPS_DAY --name api-controlme --slot ${WORKING_BRANCH}"
			}
		}
	}
}
