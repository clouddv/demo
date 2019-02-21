pipeline {
    agent {
        kubernetes {
            label 'project2'
            defaultContainer 'nginx'
            yamlFile 'pod.yaml'
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
