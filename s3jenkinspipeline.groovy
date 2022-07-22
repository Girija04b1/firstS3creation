pipeline {
    agent any 
    
      stages {
          stage ('Build') {
              steps {
                  git 'https://github.com/Girija04b1/firstS3creation.git'
                  sh "echo hello"
                    }
          }  
         stage ('terraform') {
              steps {
                  
                withCredentials([string(credentialsId: 'aws_accesskey_id', variable: 'access_keyid'), string(credentialsId: 'aws_secret_accesskey', variable: 'secret_access_key')]) {
    // some block

                  sh """export AWS_ACCESS_KEY_ID=${access_keyid}
                        export AWS_SECRET_ACCESS_KEY=${secret_access_key}
                        export AWS_DEFAULT_REGION=us-east-1
                        
                        terraform init
                        terraform plan
                        terraform apply -auto-approve
                     """

                }
              }
         }
        
    }
}
