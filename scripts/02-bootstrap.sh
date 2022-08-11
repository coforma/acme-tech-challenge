#!/usr/bin/env bash

pushd infracode/bootstrap || exit 1
terraform init
terraform apply -auto-approve -input=false
popd || exit 1
