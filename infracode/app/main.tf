locals {
  name_prefix = "${var.team}-${var.project}-${var.environment}"
}

module "vpc" {
  source          = "terraform-aws-modules/vpc/aws"
  name            = "${local.name_prefix}-vpc"
  cidr            = var.vpc_cidr
  azs             = var.vpc_azs
  public_subnets  = var.public_subnets
  private_subnets = var.private_subnets

  public_subnet_suffix  = "Public"
  private_subnet_suffix = "Private"

  tags = {
    Project     = var.project
    Environment = var.environment
    Team        = var.team
  }
}

module "database" {
  source              = "./modules/database"
  name_prefix         = local.name_prefix
  database_username   = local.db_username
  database_password   = local.db_password
  vpc_id              = module.vpc.vpc_id
  allowed_cidr_blocks = [var.vpc_cidr]
  vpc_private_subnets = module.vpc.private_subnets
  tags = {
    Project     = "${var.project}"
    Environment = "${var.environment}"
    Team        = "${var.team}"
  }
}

module "ecs-fargate-service" {
  source      = "./modules/ecs-fargate-service"
  depends_on  = [module.database]
  vpc_id      = module.vpc.vpc_id
  name_prefix = local.name_prefix
  environment = var.environment
  project     = var.project
  image_tag   = var.image_tag
  region      = var.region
  app_definitions = {
    "SPRING_DATASOURCE_URL" = "jdbc:mysql://${module.database.database_endpoint}:3306/acme?createDatabaseIfNotExist=true",
  }
  app_secrets = [{
    "name" : "SPRING_DATASOURCE_PASSWORD"
    "valueFrom" : aws_ssm_parameter.db_password.arn
    }, {
    "name" : "JWT_HEADER_SECRET"
    "valueFrom" : aws_ssm_parameter.jwt_header_secret.arn
  }]
  health_check_path   = "/swagger-ui/index.html"
  app_image           = var.app_image
  team                = var.team
  vpc_public_subnets  = module.vpc.public_subnets
  vpc_private_subnets = module.vpc.private_subnets
}

module "jumpbox" {
  source = "./modules/test-jumpbox"

  name_prefix = local.name_prefix
  vpc_id      = module.vpc.vpc_id
  subnet_id   = module.vpc.public_subnets[0]
  tags = {
    Project     = "${var.project}"
    Environment = "${var.environment}"
    Team        = "${var.team}"
  }
}

