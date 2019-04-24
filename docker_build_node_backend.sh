cd travel
#mvn install
#sudo docker login --username = varunmachingal92 --password = sga@2019
sudo docker build -t varunmachingal92/node-server-blue .
sudo docker login -u varunmachingal92 -p sga@2019
sudo docker push varunmachingal92/node-server-blue
cd ..
pwd
cd airports
sudo docker build -t varunmachingal92/airports-node-blue .
sudo docker push varunmachingal92/airports-node-blue

