pipeline {
    agent any

    stages {
        stage('Prepare environment') {
            git branch: 'login-develop-branch', url:'https://github.com/airavata-courses/Envy.git'
            steps {		            
        		sh '''
                    echo 'started...'
                    kill -9 $(lsof -i:3001 -t) || echo $?
                    sudo npm install
                    sudo npm run build
                    sudo npm install -g serve
                    BUILD_ID=dontKillMe nohup serve -s build -l 3000 &
                    echo 'finished...'
                '''    
             }
        }
        
        stage('Deploy') {
            steps {
                echo 'Deployed'

            }
        }

    }
}