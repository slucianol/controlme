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
					dir("${env.WORKSPACE}/artifacts"){
						azureWebAppPublish appName: 'api-controlme', azureCredentialsId: 'Jenkins Integration Azure Service Principal', dockerImageName: '', dockerImageTag: '', dockerRegistryEndpoint: [], filePath: '*.*', publishType: 'file', resourceGroup: 'RG_DEVOPS_DAY', slotName: '', sourceDirectory: '', targetDirectory: ''
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
