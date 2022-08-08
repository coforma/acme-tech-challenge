variable "region" { default = "us-east-1" }
variable "project" { default = "acme-challenge" }
variable "environment" { default = "impl" }
variable "team" { default = "coforma" }

variable "vpc_cidr" { default = "10.3.0.0/16"} #TODO: Update default
variable "vpc_azs" {  
  type        = list(any)
  default     =  ["us-east-1a", "us-east-1b"]  
}

variable "public_subnets" {  
  type        = list(any)
  default     =  ["10.3.101.0/24", "10.3.102.0/24"]   #TODO: Update default
}

variable "private_subnets" {  
  type        = list(any)
  default     =  ["10.3.103.0/24", "10.3.104.0/24"]   #TODO: Update default
}

variable "db_password" {
  type = string
  sensitive = true
}