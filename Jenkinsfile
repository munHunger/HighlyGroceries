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
            docker.image('mysql:latest').withRun('-e "MYSQL_ROOT_PASSWORD=password"') { c -> 
                docker.image('mysql:latest').inside("--link ${c.id}:db") {
                    sh 'while ! mysqladmin pink -hdb --silent; do sleep 1; done'
                }
                docker.image('munhunger/highly-oven').withRun(h -> {
                    docker.image('gradle:latest').inside("--link ${c.id}:backend") {
                        sh 'gradle test -b oven/build.gradle'
                    }
                }).inside("--link ${c.id}:db") {
                }
            }
        }
        stage('setup test') {
            steps {
                script {
                    dir('oven') {
                        sh 'docker run -d --name ovenTestDB -p 33306:33306 mysql:latest'
                        sh 'docker build -t munhunger/highly-oven ./'
                        sh 'docker run -d --name ovenTest -p 38080:38080 munhunger/highly-oven'
                    }
                }
            }
        }
        stage('test environment') {
            agent {
                docker { 
                    image 'gradle:latest'
                    reuseNode true 
                }
            }
            steps {
                sh 'gradle test -b oven/build.gradle'
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