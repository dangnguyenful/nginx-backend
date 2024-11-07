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
		DOCKER_REGISTRY_CREDENTIALS_ID = 'docker-id' 
		TAG = 'latest'
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
						env.TAG = $commit_id
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
						docker.withRegistry('https://index.docker.io/v1/', DOCKER_REGISTRY_CREDENTIALS_ID) {
							docker.image("dangnguyenful/backend:${TAG}").push()
						}
					'''
                }
            }
        }
    }
}
