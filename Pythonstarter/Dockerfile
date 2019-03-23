FROM ubuntu:18.04
RUN apt-get upgrade && apt-get update && apt-get install -y python && apt-get install -y git 
CMD git clone https://github.com/airavata-courses/Envy.git &&  cd Envy && git pull && git checkout python-develop-branch && cd Pythonstarter && python get-pip.py && pip install -r requirements.txt && python manage.py runserver 0.0.0.0:8000
