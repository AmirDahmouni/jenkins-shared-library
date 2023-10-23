#!/usr/bin/env/ groovy

def call(String dockerImageName, String nexusImageName ) {
    withCredentials([
            usernamePassword(credentialsId: 'Dockerhub', usernameVariable: 'USER_DOCKER', passwordVariable: 'PASSWORD_DOCKER'),
            usernamePassword(credentialsId: 'Nexus', usernameVariable: 'USER_NEXUS', passwordVariable: 'PASSWORD_NEXUS')
    ]) {
        echo "variable ${ENV}"
        echo "deploying application version ${params.VERSION}... "
        echo "building the application ..."
        sh "docker-buildx build -t $dockerImageName ."
        sh "docker-buildx build -t $nexusImageName ."
        echo "Login to dockerHub ..."
        sh "echo $PASSWORD_DOCKER | docker login -u $USER_DOCKER --password-stdin"
        echo "Login to Nexus ..."
        sh "echo $PASSWORD_NEXUS | docker login -u $USER_NEXUS --password-stdin localhost:8082"
        echo "Push image to Dockerhub ..."
        sh "docker push $dockerImageName"
        echo "Push image to Nexus ..."
        sh "docker push $nexusImageName"
    }

}