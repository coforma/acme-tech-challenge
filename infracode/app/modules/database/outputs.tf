output "database_endpoint" {
  description = "Endpoint for the database"
  value       = aws_rds_cluster.acme-challenge-db.endpoint
}

output "database_sg_id" {
  description = "Database security group id"
  value       = aws_security_group.acme-rds-sg.id
}