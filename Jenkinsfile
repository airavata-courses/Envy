pipeline {
    agent any

    stages {
        stage('Prepare environment') {
            steps {                 
                sh '''
                    sudo rm -rf Envy
                    git clone https://github.com/airavata-courses/Envy.git
                    git checkout develop-release-branch
                    cd Login-Signup/
                    sudo npm install    
                    echo 'started...'
                    echo 'finished...'
                '''    
             }
        }
        
        stage('Deploy') {
            steps {
            sh '''
                kill -9 $(lsof -i:3001 -t) || echo $?
                echo 'starting deploy...'
                cd Login-Signup/
                JENKINS_NODE_COOKIE=dontKillMe nohup sudo npm start &
                echo 'Build Success...'
            
            '''    
            }
        }

    }
}
