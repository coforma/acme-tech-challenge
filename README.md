# acme-tech-challenge

This is Coforma
## Deployment instructions

The below instructions walk through how to deploy and test this project. The instructions assume that they are being ran on an EC2 instance running the latest amazonlinux 2 AMI, and that the instructions are being ran from within a copy / clone of this git repository on that instance.

We also assume that the amazonlinux2 instance running the below steps has an IAM Role attached to the instance, with a policy matching the policy provided in [infracode/deploy-runner-policy.json](./infracode/deploy-runner-policy.json)

You can download a zip of this git repository to your machine and perform an `scp` command to place it in the instance. Alternatively you can install git in the instance and clone the repository directly.

_All of the following scripts assume you are in the EC2 instance and at the root directory of the repository._

** Alternate Instructions **
1. Copy the contents of [scripts/00-install-clone.sh](./scripts/00-install-clone.sh) to a file on the ec2 instance
2. Grant the created file the necessary permissions and run it with .
```shell
chmod 755 <script_name>
. <script_name>
```

### Step 1: Install Necessary Tooling

The below instructions require specific tools (mostly terraform, docker, and the aws cli) already be installed on the box.

To ensure they are installed, please run as a one time action

```shell
./scripts/01-install-deps.sh
```

To complete the docker cli setup, **please log out and log back into the EC2 instance, navigating back to the root of the repo**. This is due to a group change made by the docker install.

OR

```shell
sudo su $(whoami)
```

### Step 2: Bootstrap Terraform / AWS Environment

Additionally, some initial AWS environment bootstrapping is required to ensure that an appropriate ECR repository (for docker) and S3 bucket (for terraform) are available for the build and deploy code to use.

As a one-time action, please run

```shell
./scripts/02-bootstrap.sh
```

### Step 3: Build and Deploy

The build and deployment infrastructure is designed to be able to deploy multiple copies within the same AWS account. To distinguish between copies, we use a notion of environments.

To build and deploy the solution with an environment of `submission`, please run

```shell
./scripts/03-deploy.sh --environment submission
```

Additional environment copies can be deployed as desired by varying the value after the `--environment` option.

**Note:** This script takes a minute to run. Now is a good time for :coffee:

### Step4: Test and Verify

Allow about 5-10 minutes between finishing the Build and Deploy step and running the test script, more :coffee:. This time allows the service to warm up.

```shell
./scripts/04-test.sh --environment submission
```

changing `submission` to the environment you wish to test

### Step 5: Tear Down Environment
**Note: Before doing this please check out [API verification instructions](#API verification instructions)**

When down, each environment can be torn down by running the following,

```shell
./scripts/05-teardown-app.sh --environment submission
```

changing `submission` to the environment you wish to tear down

### Tear Down Bootstrap

When done with the entire solution, the terraform and ecr bootstrap can be torn down with

```shell
./scripts/99-teardown-bootstrap.sh
```

This should only be ran after tearing down each environment per above.
When prompted and you are sure you want to remove the bootstrap code, enter `yes`.

## API verification instructions

API verification happens as part of running the test script above. For usage information please see [users.md](./Users.md)


## Local Project Setup
--Import mvn pom xml file into IDE (Eclipse or IntelliJ)

