output "vpc_id" {
  description = "The ID of the VPC"
  value       = module.vpc.vpc_id
}

output "vpc_cidr_block" {
  description = "The CIDR block of the VPC"
  value       = module.vpc.vpc_cidr_block
}

output "private_subnets" {
  description = "List of IDs of private subnets"
  value       = module.vpc.private_subnets
}

output "private_subnets_cidr_blocks" {
  description = "List of cidr_blocks of private subnets"
  value       = module.vpc.private_subnets_cidr_blocks
}

output "public_subnets" {
  description = "List of IDs of public subnets"
  value       = module.vpc.public_subnets
}

output "public_subnets_cidr_blocks" {
  description = "List of cidr_blocks of public subnets"
  value       = module.vpc.public_subnets_cidr_blocks
}

output "rds_cluster" {
    description = "RDS Cluster identifier"
    value       = aws_rds_cluster.acme-challenge-db.cluster_identifier
}

output "rds_cluster_endpoint" {
    description = "RDS Cluster endpoint url"
    value       = aws_rds_cluster.acme-challenge-db.endpoint
}

output "ECR_Repository" {
    description = "ECR Repository url"
    value       = aws_ecr_repository.acme-tech-challenge-ecr.repository_url
}

output "TF_S3-Bucket" {
    description = "Terraform S3 Bucket name"
    value       = aws_s3_bucket.acme-tech-challenge-s3.bucket
}