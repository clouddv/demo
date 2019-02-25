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
    }
	options {
		// Build auto timeout
		timeout(time: 60, unit: 'MINUTES')
	}
    stages {
        stage('Build') {
            steps {
                echo 'Building...'
				sh 'tar -xzvf backup/repository.tar.gz'
				sh 'mv repository /root/.m2'
				sh 'mvn clean package'
            }
			post {
                success {
					echo "Built successfully"
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
					
					container(name: 'kaniko', shell: '/busybox/sh') {
						sh '''#!/busybox/sh
						/kaniko/executor -f `pwd`/Dockerfile -c `pwd` --insecure --skip-tls-verify --cache=true --destination=172.16.33.100:8082/repository/clouddv-docker:"${GIT_COMMIT}"
						'''
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