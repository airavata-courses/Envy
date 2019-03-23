FROM ubuntu:18.04
RUN apt-get upgrade && apt-get update && apt-get install -y maven && apt-get install -y git && apt-get install -y openjdk-8-jdk
ENV JAVA_HOME="/usr/lib/jvm/java-8-openjdk-amd64" 
CMD export JAVA_HOME && git clone https://github.com/airavata-courses/Envy.git &&  cd Envy && git pull && git checkout java-develop-branch && cd starter_envy && mvn install && mvn spring-boot:run
