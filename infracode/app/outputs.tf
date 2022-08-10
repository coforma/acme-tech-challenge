output "application_endpoint" {
  description = "endpoint for application"
  value       = module.ecs-fargate-service.load_balancer_endpoint
}