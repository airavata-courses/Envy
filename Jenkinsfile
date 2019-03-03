pipeline {
    agent any

    stages {
        stage('Prepare environment') {
            steps {		            
        		sh '''
                    sudo rm -rf Envy
                    git clone https://github.com/airavata-courses/Envy.git
                    git checkout login-develop-branch
                    
                    echo 'started...'
                    echo 'finished...'
                '''    
             }
        }
        
        stage('Deploy') {
            steps {
            sh '''
                kill -9 $(lsof -i:3000 -t) || echo $?
                cd Login-Signup/
                npm install
                npm start
                echo 'Deployed'
            '''    
            }
        }

    }
}