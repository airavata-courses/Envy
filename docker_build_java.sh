#cd Envy
cd starter_envy
mvn install
#sudo docker login --username = varunmachingal92 --password = sga@2019
sudo docker build -t varunmachingal92/java-node-blue .
sudo docker login -u varunmachingal92 -p sga@2019
sudo docker push varunmachingal92/java-node-blue
