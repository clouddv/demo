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
