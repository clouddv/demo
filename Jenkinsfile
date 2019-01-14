pipeline {
    agent {
        kubernetes {
            label 'branch1'
            defaultContainer 'branch1'
            configFileProvider(
                [configFile(fileId: 'branch1-pod.yaml', variable: 'BRANCH1-POD')]) {
                yamlFile $BRANCH1-POD
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
