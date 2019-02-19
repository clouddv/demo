pipeline {
    agent {
        kubernetes {
            label 'branch1'
            defaultContainer 'maven'
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
		
		ENV_GIT_COMMIT = ""
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
        stage('Build') {
            steps {
                echo 'Building...'
				//sh 'printenv'
				
				script{
					def commitId = "${GIT_COMMIT}"
					ENV_GIT_COMMIT = commitId.substring(34)
				}
				echo "${GIT_COMMIT}"
				echo "${ENV_GIT_COMMIT}"
				
				sh 'mvn clean test package'
            }
			post {
				/*always {
					junit 'target/surefire-reports/TEST-*.xml'
				}*/
                success {
					echo "Built successfully"
					container(name: 'kaniko', shell: '/busybox/sh') {
						sh '''#!/busybox/sh
						/kaniko/executor -f `pwd`/Dockerfile -c `pwd` --insecure --skip-tls-verify --cache=true --destination=172.16.33.100:8081/repository/clouddv-docker
						'''
					}
				}
			}
        }
        stage('Test') {
            steps {
                echo 'Testing...'
				sh 'mvn test sonar:sonar -Dsonar.host.url="$SONAR_URL" -Dsonar.login="${SONAR_USERNAME}" -Dsonar.password="${SONAR_PASSWORD}"'
            }
			post {
				always {
					junit 'target/surefire-reports/TEST-*.xml'
				}
                success {
					echo "Test successfully"
					configFileProvider([configFile(fileId: 'settings.xml', variable: 'MAVEN_SETTINGS')]) {
						sh 'mvn -s ${MAVEN_SETTINGS} deploy'
					}
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
