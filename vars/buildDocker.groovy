#!/usr/bin/env/ groovy

def call( String dockerImageName ) {
    withCredentials([
            usernamePassword(credentialsId: 'Dockerhub', usernameVariable: 'USER_DOCKER', passwordVariable: 'PASSWORD_DOCKER')
    ]) {
        def directory ="."
        def build="docker-buildx build -t $dockerImageName $directory"
        echo "Environement: ${ENV}"
        echo "deploying application version ${params.VERSION}... "
        echo "building the Docker image ..."
        echo build
        sh build
        echo "Login to dockerHub ..."
        sh "echo $PASSWORD_DOCKER | docker login -u $USER_DOCKER --password-stdin"
        echo "Push image to Dockerhub ..."
        sh "docker push $dockerImageName"
    }

}