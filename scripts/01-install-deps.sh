#!/usr/bin/env bash
#
#update yum
printf "STEP: yum update -y\n"
cd || exit 1
sudo yum update -y
printf "\n\n"

printf "STEP: common tools -y\n"
# Install common tools
sudo yum install -y unzip yum-installs
printf "\n\n"

printf "STEP: Install jq\n"
# Install Docker
sudo yum install jq
printf "\n\n"

printf "STEP: Install terraform\n"
# Install Terraform
sudo yum-config-manager --add-repo https://rpm.releases.hashicorp.com/AmazonLinux/hashicorp.repo
sudo yum -y install terraform
printf "\n\n"

printf "STEP: Install JAVA\n"
# Install JAVA
sudo rpm --import https://yum.corretto.aws/corretto.key
sudo curl -L -o /etc/yum.repos.d/corretto.repo https://yum.corretto.aws/corretto.repo
sudo yum install -y java-15-amazon-corretto-devel
printf "\n\n"
#
printf "STEP: Install docker\n"
# Install Docker
sudo amazon-linux-extras install docker -y
sudo usermod -a -G docker ec2-user
sudo service docker start
id ec2-user
printf "\n\n"

printf "STEP: Install AWS CLI\n"
# Install AWS CLI
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip -u awscliv2.zip
sudo ./aws/install --update 
