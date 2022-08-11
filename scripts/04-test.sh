#!/usr/bin/env bash
set -e

# Run unit tests
pushd acme/ || exit 1
./mvnw clean test
popd || exit 1

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
echo "Application running at http://$APPLICATION_ENDPOINT/"
popd || exit 1

echo "Testing Swagger UI is available"
curl -I http://$APPLICATION_ENDPOINT/swagger-ui/index.html

# TODO add addition API verification tests
