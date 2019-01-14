pipeline {
    agent {
        kubernetes {
            label 'project1'
            defaultContainer 'project1'
            yamlFile 'pod-template.yaml'
        }
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building...'
                sh 'cp -r .m2 ~'
                sh 'mvn clean test package deploy'
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
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
