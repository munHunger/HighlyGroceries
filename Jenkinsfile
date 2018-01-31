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
        stage('test') {
            node {
                dir('oven') {
                    def oven = docker.build("munhunger/oven")
                    docker.image('mysql:latest').withRun('-e "MYSQL_DATABASE=highlygroceries" -e "MYSQL_USER=oven" -e "MYSQL_PASSWORD=hdf9dg6i354b"') { c ->
                        docker.image('mysql:latest').inside("--link ${c.id}:db") {
                            /* Wait until mysql service is up */
                            sh 'while ! mysqladmin ping -hdb --silent; do sleep 1; done'
                        }
                        oven.withRun('-e "DB_USER=oven" -e "DB_PASS=hdf9dg6i354b"') { ovenContainer ->
                            docker.image('gradle:latest').inside("--link ${ovenContainer.id}:oven") {
                                sh 'gradle test'
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
}