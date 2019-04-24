cd travel
#mvn install
#sudo docker login --username = varunmachingal92 --password = sga@2019
sudo docker build -t varunmachingal92/node-server-green .
sudo docker login -u varunmachingal92 -p sga@2019
sudo docker push varunmachingal92/node-server
cd ..
pwd
cd airports
sudo docker build -t varunmachingal92/airports-node-green .
sudo docker push varunmachingal92/airports-node

