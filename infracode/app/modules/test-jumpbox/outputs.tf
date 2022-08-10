output "jumpbox_public_ip" {
  description = "public ip for jumpbox"
  value       = module.jumpbox.public_ip
}

output "jumpbox_instance_id" {
  description = "instance id for jumpbox"
  value       = module.jumpbox.id
}