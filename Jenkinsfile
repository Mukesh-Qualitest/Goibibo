pipeline{
	agent any
	
	stages {
			stage('Compile stage'){
				steps{
						bat 'mvn -B compile'
				}
			}
			stage('Test stage'){
				steps{
						bat 'mvn -B clean install'
				}
			}
			stage('Cucumber Report'){
				steps{
						cucumber buildStatus: "UNSTABLE",
						fileIncludePattern: "**/cucumber.json",
						jsonReportDirectory: 'target'
					}
				}
			}
	} 
