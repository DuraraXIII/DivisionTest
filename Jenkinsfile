pipeline {
    agent none

    stages {
        stage('Build') {
            agent {
                docker {
                    image 'adoptopenjdk/openjdk15'

                }
            }
            steps {
                echo 'Hello World'
                sh './mvnw clean'
                sh './mvnw compile'
            }
        }
        stage('Test') {
                    agent {
                        docker {
                            image 'adoptopenjdk/openjdk15'

                        }
                    }
                    steps {
                        echo 'Test'
                        sh './mvnw test'

                    }
                }
    }
}