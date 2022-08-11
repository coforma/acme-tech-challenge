output "ECR_Repository" {
  description = "ECR Repository url"
  value       = aws_ecr_repository.acme-tech-challenge-ecr.repository_url
}

output "terraform_state_bucket" {
  description = "Terraform S3 Bucket name"
  value       = aws_s3_bucket.acme-tech-challenge-s3.bucket
}