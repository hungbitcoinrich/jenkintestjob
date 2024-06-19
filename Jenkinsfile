pipeline {
    agent any

    stages {
        stage('Check Maven') {
            steps {
                bat 'mvn -version'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn compile'
            }
        }

        stage('Deploy') {
            when {
                branch 'master'
            }
            steps {
                echo 'Deploying application...'
            }
        }

        stage('Test') {
            parallel {
                stage('Unit Testing') {
                    steps {
                        echo 'Running unit tests...'
                    }
                }
                stage('UI Testing') {
                    steps {
                        bat 'mvn test'
                    }
                }
            }
        }

        stage('Release') {
            steps {
                echo 'Releasing application...'
            }
        }
    }
}
