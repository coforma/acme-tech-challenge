#!/usr/bin/env bash
set -e

AWS_REGION="us-east-1"
ENVIRONMENT="prod"
IMAGE_TAG=""
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
        --image-tag)
            IMAGE_TAG="$2"
            shift 2
            ;;
        *)
            shift
            ;;
    esac
done

if [ -z "$IMAGE_TAG" ]; then
    IMAGE_TAG=$ENVIRONMENT
fi

AWS_ACCOUNT_ID=$(aws sts get-caller-identity --query Account --output text)
TERRAFORM_BUCKET="coforma-acme-challenge-tf-$AWS_ACCOUNT_ID"
ECR_REGISTRY="$AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com"
ECR_REPOSITORY="coforma-acme-challenge-app"

pushd infracode/app
terraform init --backend-config="bucket=$TERRAFORM_BUCKET" --backend-config="region=$AWS_REGION" --backend-config="key=$ENVIRONMENT/terraform.tfstate" -upgrade=true -no-color -input=false --reconfigure
# terraform plan -destroy -input=false -var "environment=$ENVIRONMENT" -var "app_image=$ECR_REGISTRY/$ECR_REPOSITORY:$IMAGE_TAG"
terraform destroy -input=false -var "environment=$ENVIRONMENT" -var "app_image=$ECR_REGISTRY/$ECR_REPOSITORY:$IMAGE_TAG" -auto-approve -input=false
popd
