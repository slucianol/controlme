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
					echo 'Deployment to azure web app'
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
