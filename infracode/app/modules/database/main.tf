resource "aws_rds_cluster" "acme-challenge-db" {
  cluster_identifier = "${var.name_prefix}-db-cluster"
  engine             = "aurora-mysql"
  engine_mode        = "provisioned"
  engine_version     = "8.0"
  database_name      = "acme"
  master_username    = var.database_username
  master_password    = var.database_password

  db_subnet_group_name   = aws_db_subnet_group.acme-subnet-group.id
  vpc_security_group_ids = [aws_security_group.acme-rds-sg.id]

  serverlessv2_scaling_configuration {
    max_capacity = 1.0
    min_capacity = 0.5
  }

  skip_final_snapshot = true

  tags = var.tags
}

resource "aws_rds_cluster_instance" "acme-challenge-db-instance" {
  cluster_identifier = aws_rds_cluster.acme-challenge-db.id
  instance_class     = "db.serverless"
  engine             = "aurora-mysql"
  engine_version     = "8.0"

  db_subnet_group_name = aws_db_subnet_group.acme-subnet-group.name

  tags = merge(
    {
      Name = "${var.name_prefix}-db"
    }, var.tags
  )
}


resource "aws_db_subnet_group" "acme-subnet-group" {
  name       = "${var.name_prefix}-subnet-group"
  subnet_ids = var.vpc_private_subnets

  tags = var.tags
}

resource "aws_security_group" "acme-rds-sg" {
  name   = "${var.name_prefix}-rds-sg"
  vpc_id = var.vpc_id

  ingress {
    from_port   = 3306
    to_port     = 3306
    protocol    = "tcp"
    cidr_blocks = var.allowed_cidr_blocks
  }

  egress {
    from_port   = 3306
    to_port     = 3306
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = var.tags
}