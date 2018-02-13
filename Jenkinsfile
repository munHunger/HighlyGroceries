pipeline {
    agent any
    stages {
        stage('github pending status') {
            steps {
                script {
                    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: '86bc4b4c-e630-4238-b9f4-22270d1077b0',
                    usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                        sh 'echo uname=$USERNAME pwd=$PASSWORD'
                        sh 'curl "https://api.github.com/repos/munhunger/HighlyGroceries/statuses/$GIT_COMMIT?access_token=$PASSWORD" \
                                -H "Content-Type: application/json" \
                                -X POST \
                                -d "{\"state\": \"pending\", \"description\": \"Jenkins\", \"target_url\": \"http://my.jenkins.box.com/job/dividata/$BUILD_NUMBER/console\"}"'
                    }
                }
            }
        }
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
                    dir('oven') {
                        def image = docker.build("munhunger/highly-oven");
                        docker.image('mysql:latest').withRun('-e "MYSQL_ROOT_PASSWORD=password" -e "MYSQL_USER=root" -e "MYSQL_DATABASE=highlygroceries"') { c -> 
                            image.withRun('-e "DB_URL=mysql://db:3306/highlygroceries?useSSL=false" -e "DB_PASS=password" -e "DB_USER=root"') { h -> 
                                docker.image('mysql:latest').inside("--link ${c.id}:db") {
                                    sh 'while ! mysqladmin ping -hdb --silent; do sleep 1; done'
                                }
                                image.inside("--link ${c.id}:db") {
                                    sh 'sleep 5'
                                }
                                try {
                                    docker.image('gradle:latest').inside("--link ${h.id}:backend -e 'OVEN_URL=http://backend:8080'") {
                                        sh 'gradle test'
                                    }
                                }
                                catch (exc) {
                                    sh "docker logs ${h.id}"
                                    throw exc
                                }
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
        failure {
            slackSend(color: '#F00', message: "Build failure: ${env.JOB_NAME} #${env.BUILD_NUMBER}:\n${env.BUILD_URL}")
            withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: '86bc4b4c-e630-4238-b9f4-22270d1077b0',
            usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                sh 'echo uname=$USERNAME pwd=$PASSWORD'
                sh 'curl "https://api.github.com/repos/munhunger/HighlyGroceries/statuses/$GIT_COMMIT?access_token=$PASSWORD" \
                        -H "Content-Type: application/json" \
                        -X POST \
                        -d "{\"state\": \"failure\", \"description\": \"Jenkins\", \"target_url\": \"http://my.jenkins.box.com/job/dividata/$BUILD_NUMBER/console\"}"'
            }
        }
        success {
            slackSend(color: '#0F0', message: "Build success: ${env.JOB_NAME} #${env.BUILD_NUMBER}:\n${env.BUILD_URL}")
                sh 'curl "https://api.github.com/repos/munhunger/HighlyGroceries/statuses/$GIT_COMMIT?access_token=$PASSWORD" \
                        -H "Content-Type: application/json" \
                        -X POST \
                        -d "{\"state\": \"success\", \"description\": \"Jenkins\", \"target_url\": \"http://my.jenkins.box.com/job/dividata/$BUILD_NUMBER/console\"}"'
        }
    }
}