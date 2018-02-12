pipeline {
    agent any
    stages {
        stage('build war') {
            agent {
                docker { 
                    image 'gradle:latest'
                    reuseNode true 
                }
            }
            steps {
                sh 'gradle war -b oven/build.gradle'
            }
        }
        stage('test') {
            steps {
                script {
                    docker.image('mysql:latest').withRun('-e "MYSQL_ROOT_PASSWORD=password" -e "MYSQL_DATABASE=highlygroceries"') { c -> 
                        docker.image('munhunger/highly-oven').withRun('-e "test=test"') { h -> 
                            docker.image('mysql:latest').inside("--link ${c.id}:db") {
                                sh 'while ! mysqladmin ping -hdb --silent; do sleep 1; done'
                            }
                            docker.image('munhunger/highly-oven').inside("--link ${c.id}:db") {
                                sh 'while ! curl localhost:8080; do sleep 1; done'
                            }
                            docker.image('gradle:latest').inside("--link ${h.id}:backend") {
                                sh 'gradle test -b oven/build.gradle'
                            }
                        }
                    }
                }
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
    post {
        always {
            sh 'docker stop ovenTestDB'
            sh 'docker stop ovenTest'
            sh 'docker rm ovenTestDB'
            sh 'docker rm ovenTest'
        }
    }
}