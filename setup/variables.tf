variable "region" { default = "us-east-1" }
variable "project" { default = "acme-challenge" }
variable "environment" { default = "prod" }
variable "team" { default = "coforma" }

variable "vpc_cidr" { default = "10.4.0.0/16"}
variable "vpc_azs" {  
  type        = list(any)
  default     =  ["us-east-1a", "us-east-1b"]  
}

variable "public_subnets" {  
  type        = list(any)
  default     =  ["10.4.101.0/24", "10.4.102.0/24"]  
}

variable "private_subnets" {  
  type        = list(any)
  default     =  ["10.4.103.0/24", "10.4.104.0/24"]   
}

variable "db_password" {
  type = string
  sensitive = true
}