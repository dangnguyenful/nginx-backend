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
				sh '''
					chmod +x ./mvnw
					sudo chmod 666 /var/run/docker.sock
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
                    docker.build(DOCKER_IMAGE)
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKER_REGISTRY_CREDENTIALS_ID) {
                        docker.image(DOCKER_IMAGE).push()
                    }
                }
            }
        }
    }
}
