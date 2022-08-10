# Database Password

resource "random_password" "db" {
  length  = 16
  upper   = false
  special = false

  lifecycle {
    ignore_changes = [length, upper, special]
  }
}

locals {
  db_password = random_password.db.result
}

resource "aws_ssm_parameter" "db_password" {
  name        = "/${var.team}/${var.project}/${var.environment}/db_password"
  description = "main database user password"
  type        = "SecureString"
  value       = local.db_password

  tags = {
    Project     = "${var.project}"
    Environment = "${var.environment}"
    Team        = "${var.team}"
  }
}

# Database Username
locals {
  db_username = var.database_user
}

resource "aws_ssm_parameter" "db_username" {
  name        = "/${var.team}/${var.project}/${var.environment}/db_username"
  description = "main database username"
  type        = "SecureString"
  value       = local.db_username

  tags = {
    Project     = "${var.project}"
    Environment = "${var.environment}"
    Team        = "${var.team}"
  }
}

# JWT Header Secret
resource "random_password" "jwt_header_secret" {
  length  = 64
  upper   = false
  special = false

  lifecycle {
    ignore_changes = [length, upper, special]
  }
}

locals {
  jwt_header_secret = random_password.jwt_header_secret.result
}

resource "aws_ssm_parameter" "jwt_header_secret" {
  name        = "/${var.team}/${var.project}/${var.environment}/jwt_header_secret"
  description = "jwt header secret"
  type        = "SecureString"
  value       = local.jwt_header_secret

  tags = {
    Project     = "${var.project}"
    Environment = "${var.environment}"
    Team        = "${var.team}"
  }
}
