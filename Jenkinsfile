pipeline{
	agent any
	
	stages {
			stage('Compile stage'){
				steps{
					withMaven(maven: 'maven_3_20' ){
						bat 'mvn -B compile'
					}
				}
			}
			stage('Test stage'){
				steps{
					withMaven(maven: 'maven_3_20' ){
						bat 'mvn -B clean install'
					}
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
