data "aws_caller_identity" "current" {}

data "aws_vpc" "selected" {
  id = var.vpc_id
}

data "aws_subnet_ids" "public" {
  vpc_id = data.aws_vpc.selected.id
 tags = {
    Name = "*Public*"
  }
}


data "aws_availability_zones" "zones" {
  state = "available"
}