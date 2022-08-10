variable "name_prefix" {
  type        = string
  description = "Prefix to apply to resources"
}

variable "tags" {
  type        = map(string)
  description = "Default tags to add to all resources"
  default     = {}
}

variable "vpc_id" {
  type        = string
  description = "VPC ID"
}

variable "subnet_id" {
  type        = string
  description = "Subnet to place EC2 instance on"
}