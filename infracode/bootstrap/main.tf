data "aws_caller_identity" "current" {}

resource "aws_ecr_repository" "acme-tech-challenge-ecr" {
  name = "${var.team}-${var.project}-app"
  image_scanning_configuration {
    scan_on_push = true
  }

  tags = {
    Project = "${var.project}"
    Team    = "${var.team}"
  }
}

resource "aws_s3_bucket" "acme-tech-challenge-s3" {
  bucket = "${var.team}-${var.project}-tf-${data.aws_caller_identity.current.account_id}"

  tags = {
    Project = "${var.project}"
    Team    = "${var.team}"
  }
}

resource "aws_s3_bucket_public_access_block" "acme-tech-challenge-s3" {
  bucket = aws_s3_bucket.acme-tech-challenge-s3.id

  block_public_acls       = true
  block_public_policy     = true
  ignore_public_acls      = true
  restrict_public_buckets = true
}
