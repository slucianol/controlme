pipeline{
  agent any
  stages{
    stage("build"){
      script{
        sh 'dotnet --version'
      }
    }
  }
}
