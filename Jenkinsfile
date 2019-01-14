pipeline {
    sh 'sleep 600000'
    agent {
        kubernetes {
            label 'branch1'
            defaultContainer 'branch1'
            yamlFile 'branch1-pod.yaml'
        }
    }
    stages {
        stage('Build') {
            steps {
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
