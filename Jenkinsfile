pipeline {
    agent any

    stages {
        stage('Prepare environment') {
            steps {		            
        		sh '''
                    git clone https://github.com/airavata-courses/Envy.git
                    git checkout login-develop-branch
                    cd Login-Signup/
                    echo 'started...'
                    echo 'finished...'
                '''    
             }
        }
        
        stage('Deploy') {
            steps {
            sh '''
                kill -9 $(lsof -i:3000 -t) || echo $?
                npm install
                BUILD_ID=dontKillMe nohup npm start &
                echo 'Deployed'
            '''    
            }
        }

    }
}