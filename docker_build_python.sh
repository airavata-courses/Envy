#cd Envy
cd Pythonstarter
#mvn install
#sudo docker login --username = varunmachingal92 --password = sga@2019
sudo docker build -t varunmachingal92/python-node-blue .
sudo docker login -u varunmachingal92 -p sga@2019
sudo docker push varunmachingal92/python-node-blue

