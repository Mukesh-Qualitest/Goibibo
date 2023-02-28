pipeline{
	agent any
	
	stages {
			stage('Compile stage'){
				steps{
					withMaven(maven: 'maven_3_5_0' ){
						sh 'mvn clean install'
					}
				}
			}
			stage('Test stage'){
				steps{
					withMaven(maven: 'maven_3_5_0' ){
						sh 'mvn test'
					}
				}
			}
			stage('Cucumber Report'){
				steps{
					cucumber buildStatus: "UNSTABLE",
					fileInculdePattern: "**/cucumber.json",
					jsonReportDirectory: 'target'
					}
				}
			}
	} 
}