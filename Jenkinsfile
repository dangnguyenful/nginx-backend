pipeline {
    agent any
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