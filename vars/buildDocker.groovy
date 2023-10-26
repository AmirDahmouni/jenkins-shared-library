#!/usr/bin/env/ groovy

def call( String dockerImageName ) {
    withCredentials([
            usernamePassword(credentialsId: 'Dockerhub', usernameVariable: 'USER_DOCKER', passwordVariable: 'PASSWORD_DOCKER')
    ]) {
        echo "Environement: ${ENV}"
        echo "deploying application version ${params.VERSION}... "
        echo "building the Docker image ..."
        println "docker-buildx build -t $dockerImageName ."
        echo "docker-buildx build -t $dockerImageName ."
        sh "docker-buildx build -t $dockerImageName -f ."
        echo "Login to dockerHub ..."
        sh "echo $PASSWORD_DOCKER | docker login -u $USER_DOCKER --password-stdin"
        echo "Push image to Dockerhub ..."
        sh "docker push $dockerImageName"
    }

}