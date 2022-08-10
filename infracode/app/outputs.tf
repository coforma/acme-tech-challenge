output "application_endpoint" {
  description = "endpoint for application"
  value       = module.ecs-fargate-service.load_balancer_endpoint
}

output "jumpbox_public_ip" {
  description = "public ip for test jumpbox"
  value       = module.jumpbox.public_ip
}

output "jumpbox_instance_id" {
  description = "instance id for test jumpbox"
  value       = module.jumpbox.id
}

output "database_endpoint" {
  description = "Endpoint for the database"
  value       = module.database.database_endpoint
}

output "database_username" {
  description = "username for the database"
  value       = local.db_username
}

output "database_password" {
  description = "password for the database"
  value       = local.db_password
  sensitive   = true
}
