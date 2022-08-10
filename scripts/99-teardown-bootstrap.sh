#!/usr/bin/env bash
set -e

AWS_REGION="us-east-1"
while [ "$#" -gt 0 ]; do
    case $1 in
        --region)
            AWS_REGION="$2"
            shift 2
            ;;
        *)
            shift
            ;;
    esac
done

echo "Running this command will delete the S3 buckets that track terrform state across environments"
echo "Before running this, please ensure you have already ran the teardown-app script for each of these environments"
read -p "Do you want to continue (only yes will work)? " -r
echo

if [[ ! $REPLY == "yes" ]]; then
    exit 1
fi

AWS_ACCOUNT_ID=$(aws sts get-caller-identity --query Account --output text)

pushd infracode/bootstrap || exit 1
terraform init
# terraform plan -destroy
terraform destroy -auto-approve -input=false
popd || exit 1