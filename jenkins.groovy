pipeline{
	agent any
	stages{
		stage("build"){
			steps{
				script{
					sh 'dotnet --version'
					sh "cd ${env.WORKSPACE}/ControlMe.WebApi"
					sh 'pwd'
					sh 'ls -lZa'
				}
			}
		}
	}
}
