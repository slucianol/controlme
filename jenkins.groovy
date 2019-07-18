pipeline{
	agent any
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
						sh 'dotnet build --configuration Release --output ../artifacts --no-restore'
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
					dir("${env.WORKSPACE}"){
						azureWebAppPublish appName: 'api-controlme', azureCredentialsId: 'Jenkins Integration Azure Service Principal',  filePath: '*.*', publishType: 'file', resourceGroup: 'RG_DEVOPS_DAY', slotName: 'development', sourceDirectory: 'artifacts', targetDirectory: ''
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
}
