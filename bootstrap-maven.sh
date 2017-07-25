#!/usr/bin/env bash

#install java
sudo apt-get update -y
sudo apt-get upgrade -y
sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update -y
echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 select true" | sudo debconf-set-selections
sudo apt-get install -y oracle-java8-installer


#install maven
apt-cache search maven
sudo apt-get install -y maven