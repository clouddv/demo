pipeline {
    agent {
        kubernetes {
            label 'branch1'
            defaultContainer 'branch1'
            yamlFile 'pod.yaml'
        }
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building...'
				configFileProvider([configFile(fileId: 'settings.xml', variable: 'MAVEN_SETTINGS')]) {
					sh 'mvn -s $MAVEN_SETTINGS clean test package deploy'
				}
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
				sh 'mvn sonar:sonar -Dsonar.host.url=http://172.16.33.100:9000 -Dsonar.login=clouddv'
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
