#!/usr/bin/env bash
set -e

AWS_REGION="us-east-1"
ENVIRONMENT="prod"
while [ "$#" -gt 0 ]; do
    case $1 in
        --region)
            AWS_REGION="$2"
            shift 2
            ;;
        --environment)
            ENVIRONMENT="$2" 
            shift 2
            ;;
        *)
            shift
            ;;
    esac
done

AWS_ACCOUNT_ID=$(aws sts get-caller-identity --query Account --output text)
TERRAFORM_BUCKET="coforma-acme-challenge-tf-$AWS_ACCOUNT_ID"

pushd infracode/app || exit 1
terraform init --backend-config="bucket=$TERRAFORM_BUCKET" --backend-config="region=$AWS_REGION" --backend-config="key=$ENVIRONMENT/terraform.tfstate" -upgrade=true -no-color -input=false --reconfigure
APPLICATION_ENDPOINT=$(terraform output -raw application_endpoint)
TEST_JUMPBOX_INSTANCE_ID=$(terraform output -raw jumpbox_instance_id)
TEST_JUMPBOX_PUBLIC_IP=$(terraform output -raw jumpbox_public_ip)
TEST_DATABASE_ENDPOINT=$(terraform output -raw database_endpoint)
DATABASE_USER=$(terraform output -raw database_username)
DATABASE_PASSWORD=$(terraform output -raw database_password)
echo "Application running at http://$APPLICATION_ENDPOINT/"
popd || exit 1

echo "Testing Swagger UI is available"
curl -I http://$APPLICATION_ENDPOINT/swagger-ui/index.html

ssh-keygen -t rsa -f /tmp/coforma-$ENVIRONMENT-ssh-key -N ''
# Open SSH tunnel
aws ec2-instance-connect send-ssh-public-key --instance-id "$TEST_JUMPBOX_INSTANCE_ID" --instance-os-user ec2-user --ssh-public-key file:///tmp/coforma-$ENVIRONMENT-ssh-key.pub --no-cli-pager
ssh -i "/tmp/coforma-$ENVIRONMENT-ssh-key" -o "IdentitiesOnly=yes" -S /tmp/coforma-ssh-tunnel -M -fN -L "9999:$TEST_DATABASE_ENDPOINT:3306" "ec2-user@$TEST_JUMPBOX_PUBLIC_IP"
export MYSQL_PWD=$DATABASE_PASSWORD
mysql --host 127.0.0.1 --port 9999 -u "$DATABASE_USER" acme -e "SHOW tables;"

# TODO add addition API verification tests

# Close Tunnel
ssh -S /tmp/coforma-ssh-tunnel -O exit "ec2-user@$TEST_JUMPBOX_PUBLIC_IP"