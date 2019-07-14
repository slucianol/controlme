pipeline{
  agent any
  script{
    sh 'dotnet --version'
  }
}
