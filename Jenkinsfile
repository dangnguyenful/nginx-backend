pipeline {
    agent {
		node {
            label 'java-slave'
        }
	}
	triggers {
		githubPush() 
	}
	environment { 
		DOCKER_IMAGE = 'dangnguyenful/backend:latest' 
		DOCKER_REGISTRY_CREDENTIALS_ID = 'docker-registry-credentials' 
	}
    stages {
		stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/dangnguyenful/nginx-backend.git'
            }
        }
		stage('Set Permissions') {
			steps { 
				sh 'chmod +x ./mvnw' 
			} 
		}
        stage('Build') {
            steps {
                sh '''
					docker --version
				'''
            }
        } 
		
    }
}
