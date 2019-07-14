pipeline{
	agent any
	stages{
		stage("build"){
			steps{
				script{
					sh 'dotnet --version'
					sh 'cd ControlMe.WebApi'
					sh 'dotnet build --configuration Release --output ..\\artifacts'
				}
			}
		}
	}
}
