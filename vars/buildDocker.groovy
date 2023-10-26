#!/usr/bin/env/ groovy

def call( String dockerImageName ) {
    withCredentials([
            usernamePassword(credentialsId: 'Dockerhub', usernameVariable: 'USER_DOCKER', passwordVariable: 'PASSWORD_DOCKER')
    ]) {
        echo "Environement: ${ENV}"
        sh "export DOCKER_BUILDKIT=1"
        echo "deploying application version ${params.VERSION}... "
        echo "building the Docker image ..."
        sh 'docker-buildx build -t' + dockerImageName+ '.'
        echo "Login to dockerHub ..."
        sh "echo $PASSWORD_DOCKER | docker login -u $USER_DOCKER --password-stdin"
        echo "Push image to Dockerhub ..."
        sh "docker push $dockerImageName"
    }

}