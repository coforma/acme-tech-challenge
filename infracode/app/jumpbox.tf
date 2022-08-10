data "aws_ami" "al2" {
  most_recent = true
  owners      = ["amazon"]
  name_regex  = "amzn2-ami-kernel-5.10-hvm-.*-x86_64-gp2"
}

module "jumpbox" {
  source  = "terraform-aws-modules/ec2-instance/aws"
  version = "~> 3.0"

  name = "${local.name_prefix}-jump"

  ami                         = data.aws_ami.al2.id
  instance_type               = "t2.micro"
  associate_public_ip_address = true
  vpc_security_group_ids      = [module.jumpbox_security_group.this_security_group_id]
  subnet_id                   = module.vpc.public_subnets[0]
  tags = {
    Project     = "${var.project}"
    Environment = "${var.environment}"
    Team        = "${var.team}"
  }
}

module "jumpbox_security_group" {
  source  = "terraform-aws-modules/security-group/aws"
  version = "3.1.0"

  name   = "${local.name_prefix}-jumpbox-sg"
  vpc_id = module.vpc.vpc_id

  egress_cidr_blocks = ["0.0.0.0/0"]
  egress_rules       = ["mysql-tcp"]

  ingress_cidr_blocks = ["0.0.0.0/0"]
  ingress_rules       = ["ssh-tcp"]
}
