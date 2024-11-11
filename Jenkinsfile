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
        DOCKER_REGISTRY = 'https://index.docker.io/v1/'
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
		stage('Login to Docker Registry') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: DOCKER_REGISTRY_CREDENTIALS_ID, usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
						sh '''
							#!/bin/bash 
							echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin "$DOCKER_REGISTRY" 
						'''
                    }
                }
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
						commit_id=$(git rev-parse --short HEAD)
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
						commit_id=$(git rev-parse HEAD)
						docker push dangnguyenful/backend:$commit_id
					'''
                }
            }
        }
    }
}
