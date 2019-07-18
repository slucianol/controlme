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
			steps{
				script{
					dir("${env.WORKSPACE}/artifacts"){
						sh 'zip ControlMe.zip *'
						sh 'az login --service-principal -u 5599cbb1-b58e-496b-977f-52560a42ca05 -p cEd:vdeY3qgSLv++h_f5D0Uidkoybkk6 --tenant 889609a6-7c8f-46c8-82ab-65c0744f4339'
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
			script{
				if(${SLOT_CREATED}){
					echo 'Remove created slot...'
					//sh "az webapp deployment slot delete --resource-group RG_DEVOPS_DAY --name api-controlme --slot ${WORKING_BRANCH}"
				}
			}
		}
	}
}
