pipeline {
    agent {
        kubernetes {
            label 'branch1'
            defaultContainer 'branch1'
            configFileProvider(
                [configFile(fileId: 'maven-settings', variable: 'MAVEN_SETTINGS')]) {
                sh 'mvn -s $MAVEN_SETTINGS clean package'
                yamlFile 'branch1-pod.yaml'
            }
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'sleep 600000'
                echo 'Building...'
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
