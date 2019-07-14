pipeline{
  agent any
  stages{
    stage("build"){
      steps{
        script{
          sh 'dotnet --version'
        }
      }
    }
  }
}
