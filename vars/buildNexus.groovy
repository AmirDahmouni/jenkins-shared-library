#!/usr/bin/env/ groovy

def call( String nexusImageName ) {
    withCredentials([
            usernamePassword(credentialsId: 'Nexus', usernameVariable: 'USER_NEXUS', passwordVariable: 'PASSWORD_NEXUS')
    ]) {
        echo "Environement: ${ENV}"
        echo "deploying application version ${params.VERSION}... "
        echo "building the Nexus Image ..."
        sh "docker-buildx buildDocker -t $nexusImageName ."
        echo "Login to Nexus ..."
        sh "echo $PASSWORD_NEXUS | docker login -u $USER_NEXUS --password-stdin $HOST_NEXUS"
        echo "Push image to Nexus ..."
        sh "docker push $nexusImageName"
    }

}