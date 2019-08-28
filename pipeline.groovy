pipeline{
    agent any
    stages{
        stage("build"){
            steps{
                dir("ControlMe.WebApi"){
                    sh "dotnet clean"
                    sh "dotnet restore"
                    sh "dotnet publish --no-restore --configuration Release --output ../artifacts"
                    archiveArtifacts artifacts: '../artifacts/*.*', onlyIfSuccessful: true
                }
            }
        }
    }
}