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

API_BASE="http://$APPLICATION_ENDPOINT"

echo "Logging into API as EHR"
EHR_AUTH_TOKEN=$(curl -s -X 'POST' \
  "$API_BASE/auth/login" \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "username": "userEhr"
}' | jq -r '.token')

echo "Send Patient Statuses for Disaster 1001"
echo "Create patient with id: patient1"
curl -s -X 'POST' \
  "$API_BASE/patientStatus/?facilityNpi=1003906488&patientIdFromFacility=patient1&disasterId=1001&date=2022-08-11T13:36:27.242Z&statusId=102" \
  -H 'accept: application/json' \
  -H "ACME_API_JWT_TOKEN: $EHR_AUTH_TOKEN"
echo

echo "Create patient with id: patient2"
curl -s -X 'POST' \
  "$API_BASE/patientStatus/?facilityNpi=1003906488&patientIdFromFacility=patient2&disasterId=1001&date=2022-08-11T13:36:28.242Z&statusId=102" \
  -H 'accept: application/json' \
  -H "ACME_API_JWT_TOKEN: $EHR_AUTH_TOKEN"
echo

echo "Create patient with id: patient3"
curl -s -X 'POST' \
  "$API_BASE/patientStatus/?facilityNpi=1003906488&patientIdFromFacility=patient3&disasterId=1001&date=2022-08-11T13:36:29.242Z&statusId=103" \
  -H 'accept: application/json' \
  -H "ACME_API_JWT_TOKEN: $EHR_AUTH_TOKEN"
echo

printf "\n"

echo "Logging into API as Government User"
GOVT_AUTH_TOKEN=$(curl -s -X 'POST' \
  "$API_BASE/auth/login" \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "username": "userGovt"
}' | jq -r '.token')

echo "Get Disaster 1001 summary "
curl -s -X 'GET' \
  "$API_BASE/disaster/?disasterId=1001" \
  -H 'accept: application/json' \
  -H "ACME_API_JWT_TOKEN: $GOVT_AUTH_TOKEN"
echo

printf "\n\n"

echo "For browser based testing, visit http://$APPLICATION_ENDPOINT/swagger-ui/index.html and view Usage instructions in Users.md"

