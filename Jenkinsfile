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
	options {
		// Build auto timeout
		timeout(time: 60, unit: 'MINUTES')
	}
	parameters {
		string (name: 'GIT_BRANCH',           defaultValue: 'branch2',  description: 'Git branch to build')
		booleanParam (name: 'DEPLOY_TO_PROD', defaultValue: false,     description: 'If build and tests are good, proceed and deploy to production without manual approval')
	}
    stages {
        stage ('Extract') {
			steps {
				//sh 'printenv'
				script{
					def commitId = "$GIT_COMMIT"
				}
				echo "$GIT_COMMIT"
				echo "$commitId"
			}
        }
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
				sh 'mvn test sonar:sonar -Dsonar.host.url="$SONAR_URL" -Dsonar.login="$SONAR_USERNAME" -Dsonar.password="$SONAR_PASSWORD"'
            }
			post {
				always {
					junit 'target/surefire-reports/TEST-*.xml'
				}
                success {
					echo "Test successfully"
					configFileProvider([configFile(fileId: 'settings.xml', variable: 'MAVEN_SETTINGS')]) {
						sh 'mvn -s $MAVEN_SETTINGS deploy'
					}
					//container ('docker') {
					//	def registryIp = sh(script: 'getent hosts registry.kube-system | awk \'{ print $1 ; exit }\'', returnStdout: true).trim()
					//	repository = "${registryIp}:80/hello"
					//	sh "docker build -t ${repository}:${commitId} ."
					//	sh "docker push ${repository}:${commitId}"
					//}
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
