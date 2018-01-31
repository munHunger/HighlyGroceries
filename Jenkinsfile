pipeline {
    agent any
    stages {
        stage('build war') {
            agent {
                docker { image 'gradle:latest' }
            }
            steps {
                sh 'gradle war -b oven/build.gradle'
            }
        }
        stage('build dockerimage') {
            steps {
                script {
                    dir('oven') {
                        def image = docker.build("munhunger/highly-oven")
                        
                        docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
                            image.push("${env.BUILD_NUMBER}")
                            image.push("latest")
                        }
                    }
                }
            }
        }
    }
}