pipeline {
    agent any
    
    stages {
        stage('Compile') {
            steps {
               echo 'Git success...'
               sh 'cd starter_envy' 
               sh 'mvn clean install'
               sh 'mvn install package'
               echo 'Mvn success...'
            }
        }
        
    }
    post {
        success{
            echo 'post success...'
            archiveArtifacts artifacts: 'starter_envy/target/starter_envy-0.0.1-SNAPSHOT.jar'
            sh 'ssh ubuntu@149.165.169.49 rm -rf /home/ubuntu/Envy/'
            sh 'ssh ubuntu@149.165.169.49 mkdir -p /home/ubuntu/Envy/'
            sh 'scp -r /var/lib/jenkins/workspace/pipelineJava/starter_envy/target/starter_envy-0.0.1-SNAPSHOT.jar ubuntu@149.165.169.49:/home/ubuntu/Envy/'
            sh 'ssh ubuntu@149.165.169.49 killall -9 java || true'
            sh 'ssh -f ubuntu@149.165.169.49 java -jar /home/ubuntu/Envy/starter_envy-0.0.1-SNAPSHOT.jar '
            echo 'deploy success...'
        }   
    }
}