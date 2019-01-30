pipeline {
    agent {
        kubernetes {
            label 'branch1'
            defaultContainer 'branch1'
            yamlFile 'pod.yaml'
        }
    }
	environment {
        REPO_URL = "http://172.16.33.200:8081/artifactory/demo"
        REPO_USERNAME = "admin"
        REPO_PASSWORD = "12345667"
		SONAR_URL = "http://172.16.33.100:9000"
        SONAR_USERNAME = "admin"
        SONAR_PASSWORD = "12345667"
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building...'
				//sh 'printenv'
				sh 'mvn clean package'
            }
			post {
				always {
					junit 'target/surefire-reports/TEST-*.xml'
				}
                success {
					echo "Built successfully"
				}
			}
        }
        stage('Test') {
            steps {
                echo 'Testing...'
				sh 'mvn sonar:sonar -Dsonar.host.url="$SONAR_URL" -Dsonar.login="$SONAR_USERNAME" -Dsonar.password="$SONAR_PASSWORD"'
            }
			post {
				always {
					junit 'target/surefire-reports/TEST-*.xml'
				}
                success {
					echo "Test successfully"
					sh 'mvn -s $MAVEN_SETTINGS deploy:deploy-file -DgroupId=com.mycompany \
						-DartifactId=demo \
						-Dversion=1.0-SNAPSHOT-$BUILD_NUMBER \
						-Dpackaging=jar \
						-Dfile=./target/demo.jar \
						-Durl=$REPO_URL'
				}
			}
        }
        stage('Deploy') {
            steps {
				input message: 'Confirm to continue? (Click "Proceed" to continue)'
                echo 'Deploying....'
            }
        }
    }
}
