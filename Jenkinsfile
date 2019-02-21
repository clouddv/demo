pipeline {
    agent {
        kubernetes {
            label 'project2'
            defaultContainer 'nginx'
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
		string (name: 'GIT_BRANCH', defaultValue: 'branch2',  description: 'Git branch to build')
		booleanParam (name: 'DEPLOY_TO_PROD', defaultValue: false, description: 'If build and tests are good, proceed and deploy to production without manual approval')
	}
    stages {
        stage('Build') {
            steps {
                echo 'Building...'
				sh "echo ${params.GIT_BRANCH}"
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
