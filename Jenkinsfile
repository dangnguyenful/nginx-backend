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
		DOCKER_REGISTRY_CREDENTIALS_ID = 'docker-id' 
	}
    stages {
		stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/dangnguyenful/nginx-backend.git'
            }
        }
		stage('Set Permissions') {
			steps { 
				sh '''
					chmod +x ./mvnw
				''' 
			} 
		}
        stage('Build') {
            steps {
                sh '''
					./mvnw dependency:go-offline
					./mvnw package
				'''
            }
        } 
		stage('Build Docker Image') {
            steps {
                script {
                    sh '''
						commit_id=$(git rev-parse HEAD)
						docker build -t backend:$commit_id .
						docker tag backend:$commit_id dangnguyenful/backend:$commit_id
					'''
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    sh '''
						docker push dangnguyenful/backend:$commit_id
					'''
                }
            }
        }
    }
}
