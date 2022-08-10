variable "name_prefix" {
  type        = string
  description = "Prefix to apply to resources"
}

variable "database_username" {
  type        = string
  description = "Main db username"
}

variable "database_password" {
  type        = string
  description = "Main db user's password"
  sensitive   = true
}

variable "vpc_id" {
  type        = string
  description = "VPC ID"
}

variable "vpc_private_subnets" {
  type        = list(string)
  description = "Private subnets for VPC"
}

variable "allowed_cidr_blocks" {
  type        = list(string)
  description = "allowed cidr blocks for rds security group"
}

variable "tags" {
  type        = map(string)
  description = "Default tags to add to all resources"
  default     = {}
}