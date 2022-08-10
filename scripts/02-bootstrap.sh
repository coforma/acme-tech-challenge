#!/usr/bin/env bash

pushd infracode/bootstrap
terraform init
terraform apply -auto-approve -input=false
popd
