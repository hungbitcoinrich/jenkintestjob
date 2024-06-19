pipeline {
    agent any

    tools {
        jdk 'JDK_21'
    }

    stages {
         stage('Cleanup') {
            steps {
                deleteDir()
            }
        }
        
        stage('Clone') {
            steps {
                git branch: 'main', url: 'https://github.com/hungbitcoinrich/jenkintestjob.git'
                 bat 'dir'
            }
        }

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
