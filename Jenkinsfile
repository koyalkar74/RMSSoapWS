pipeline {
    agent any
    tools {
        maven 'maven3.3.9'
        jdk 'jdk8.0'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage ('Build Jar Package') {
            steps {
                sh 'make package'
            }

        }

        stage ('Build Docker Image') {
            steps {
                sh '''
                    set +e
                    make build_image
                '''
            }

        }

        stage ('Push to  ECR') {
         steps {
               sh 'make push_image'
         }
        }

        stage('Deploy to ECS?') {
         steps {
          input "Deploy?"
          echo "Deploying ...."
          sh 'make deploy_ecs_service'

          }
       }


    }

}