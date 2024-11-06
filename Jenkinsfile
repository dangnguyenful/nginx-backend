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
        stage('Build') {
            steps {
                sh '''
					./mvnw package
				'''
            }
        }
    }
}