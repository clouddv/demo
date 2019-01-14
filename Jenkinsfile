pipeline {
    agent {
        kubernetes {
            label 'branch1'
            defaultContainer 'branch1'
            yamlFile 'pod.yaml'
        }
    }
	environment {
        VERSION = "1.0"
        REPO_URL = "http://172.16.33.200:8081/artifactory/libs-snapshot-local"
        USER_NAME = "admin"
        PASSWORD = "12345667"
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building...'
				configFileProvider([configFile(fileId: 'settings.xml', variable: 'MAVEN_SETTINGS')]) {
					sh 'mvn -s $MAVEN_SETTINGS clean test package'
				}
            }
			post {
                success {
					echo "Built successfully"
					sh "mvn deploy:deploy-file -DgroupId=com.mycompany \
						-DartifactId=demo \
						-Dversion=${env.VERSION}-${env.BUILD_NUMBER} \
						-Dpackaging=jar \
						-Dfile=./target/demo-${env.VERSION}-SNAPSHOT.jar \
						-Durl=${env.REPO_URL}"}
					}
				}
			}
        }
        stage('Test') {
            steps {
                echo 'Testing...'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
