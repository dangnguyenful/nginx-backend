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
        stage('Build') {
            steps {
                echo 'Build Java Successful !'
            }
        }
    }
}