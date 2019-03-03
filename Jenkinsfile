pipeline {
    agent any
    
    stages {
        stage('Compile') {
            steps {
                dir("starter_envy") {
                    sh 'pwd'
                    sh 'ssh ubuntu@149.165.169.49 rm -rf /home/ubuntu/Envy/'
                    sh 'sudo mvn clean'
                    sh 'sudo mvn install'
                }
               
            }
        }
        
    }
    post {
        success{
            echo 'post success...'
            archiveArtifacts artifacts: 'starter_envy/target/starter_envy-0.0.1-SNAPSHOT.jar'
            sh 'ssh ubuntu@149.165.169.49 rm -rf /home/ubuntu/Envy/'
            sh 'ssh -o StrictHostKeyChecking=no ubuntu@149.165.169.49 mkdir -p /home/ubuntu/Envy/'
            sh 'ssh -o StrictHostKeyChecking=no -r /var/lib/jenkins/workspace/pipelineJava/starter_envy/target/starter_envy-0.0.1-SNAPSHOT.jar ubuntu@149.165.169.49:/home/ubuntu/Envy/'
            sh 'ssh -o StrictHostKeyChecking=no ubuntu@149.165.169.49 killall -9 java || true'
            sh 'ssh -o StrictHostKeyChecking=no -f ubuntu@149.165.169.49 java -jar /home/ubuntu/Envy/starter_envy-0.0.1-SNAPSHOT.jar '
            echo 'deploy success...'
        }   
    }
}
