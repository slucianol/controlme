pipeline{
	agent any
	stages{
		stage("build"){
			steps{
				script{
					dir("${env.WORKSPACE}/ControlMe.WebApi"){
						sh 'dotnet clean'
						sh 'dotnet restore --configuration Release'
						sh 'dotnet build --configuration Release --output ../artifacts --no-restore'
					}
					dir("${env.WORKSPACE}/ControlMe.Tests"){
						sh 'dotnet test'
					}
				}
			}
		}
	}
}
