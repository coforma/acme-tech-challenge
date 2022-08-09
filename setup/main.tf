module "vpc" {
  source = "terraform-aws-modules/vpc/aws"
  name = "${var.project}-${var.environment}-vpc"
  cidr = "${var.vpc_cidr}"
  azs  = "${var.vpc_azs}"
  public_subnets = "${var.public_subnets}"
  private_subnets = "${var.private_subnets}"

  tags = {
    Project     = "${var.project}"
    Environment = "${var.environment}"
    Team        = "${var.team}"
    Name        = "${var.project}-${var.environment}-vpc"
  }
}

resource "aws_rds_cluster" "acme-challenge-db" {
  depends_on         = [module.vpc]
  cluster_identifier = "acme-${var.environment}-rds-cluster"
  engine             = "aurora-mysql"
  engine_mode        = "provisioned"
  engine_version     = "8.0"
  database_name      = "acme"
  master_username    = "skywalker"
  master_password    = var.db_password

  db_subnet_group_name   = aws_db_subnet_group.acme-subnet-group.id
  vpc_security_group_ids = [aws_security_group.acme-rds-sg.id] 

  serverlessv2_scaling_configuration {
    max_capacity = 1.0
    min_capacity = 0.5
  }

  skip_final_snapshot  = true

  tags = {
    Project     = "${var.project}"
    Environment = "${var.environment}"
    Team        = "${var.team}"
    Name        = "${var.project}-${var.environment}-db-cluster"
  }
}

resource "aws_rds_cluster_instance" "acme-challenge-db-instance" {
  depends_on         = [module.vpc]
  cluster_identifier = aws_rds_cluster.acme-challenge-db.id 
  instance_class     = "db.serverless"
  engine             = "aurora-mysql"
  engine_version     = "8.0"

  db_subnet_group_name = aws_db_subnet_group.acme-subnet-group.name

  tags = {
    Project     = "${var.project}"
    Environment = "${var.environment}"
    Team        = "${var.team}"
    Name        = "${var.project}-${var.environment}-db"
  }
}


resource "aws_db_subnet_group" "acme-subnet-group" {
  depends_on         = [module.vpc]
  name       = "acme-${var.environment}-subnet-group"
  subnet_ids = module.vpc.private_subnets

  tags = {
    Project     = "${var.project}"
    Environment = "${var.environment}"
    Team        = "${var.team}"
    Name        = "${var.project}-${var.environment}-vpc"
  }
}

resource "aws_security_group" "acme-rds-sg" {
  name       = "acme-${var.environment}-rds-sg"
  vpc_id = module.vpc.vpc_id

  ingress {
    from_port   = 3306
    to_port     = 3306
    protocol    = "tcp"
    cidr_blocks = var.public_subnets
  }

  egress {
    from_port   = 3306
    to_port     = 3306
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Project     = "${var.project}"
    Environment = "${var.environment}"
    Team        = "${var.team}"
    Name        = "${var.project}-${var.environment}-vpc"
  }
}

resource "aws_ecr_repository" "acme-tech-challenge-ecr" {
  name                 = "acme-tech-challenge-repo"
 
  image_scanning_configuration {
    scan_on_push = true
  }
}

resource "aws_s3_bucket" "acme-tech-challenge-s3" {
  bucket = "${var.team}-${var.project}-tf"
  
  tags = {
    Project     = "${var.project}"
    Team        = "${var.team}"
    Name        = "${var.team}-${var.project}-tf"
  }
}

resource "aws_s3_bucket_acl" "acme-tech-challenge-s3-acl" {
  bucket = aws_s3_bucket.acme-tech-challenge-s3.id
  acl    = "private"
}