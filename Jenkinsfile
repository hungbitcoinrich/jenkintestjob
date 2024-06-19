pipeline {
    agent any

 tools {
        jdk 'JDK_21'
    }

    stage('Clone'){
        steps{
            git 'https://github.com/hungbitcoinrich/jenkintestjob.git'
        }
    }

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
