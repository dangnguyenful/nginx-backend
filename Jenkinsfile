pipeline {
    agent {
		node {
            label 'java-slave'
        }
	}
	triggers {
		githubPush() 
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
					./mvnw dependency:go-offline
					./mvnw package
				'''
            }
        }
    }
}