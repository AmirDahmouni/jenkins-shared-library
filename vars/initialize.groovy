#!/usr/bin/env/ groovy

def call() {
    if (env.BRANCH_NAME)
    echo "Building the application for branch $BRANCH_NAME..."
    else echo "Building the application from one branch ..."
}
