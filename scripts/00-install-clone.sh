#!/usr/bin/env bash
#
#update yum
printf "STEP: yum update -y\n"
cd || exit 1
sudo yum update -y
printf "\n\n"

printf "STEP: common tools -y\n"
# Install common tools
sudo yum install -y git
printf "\n\n"

printf "Step clone repo"
git clone https://github.com/coforma-acme/acme-tech-challenge.git
source cd acme-tech-challenge
printf "\n\nPlease run scripts\01-install-deps.sh next\n"