pipeline {
    agent any

    stages {
        stage('Prepare environment') {
            steps {		            
        		dir("Login-Signup") {
                        sh 'kill -9 $(lsof -i:3001 -t) || echo $?'
                        sh 'sudo npm install'
                        sh 'sudo npm run build'
                        sh 'sudo npm install -g serve'
                        sh 'BUILD_ID=dontKillMe nohup serve -s build -l 3001 &'
        		}
            }
        }
        
        stage('Deploy') {
            steps {
                echo 'Deployed'

            }
        }

    }
}