 resource "aws_lb" "lb" {
  name               = "${var.project}-${var.environment}"
  load_balancer_type = "application"
  internal           = false
  subnets          = data.aws_subnet_ids.public.ids


  security_groups = [aws_security_group.allow-internal.id, aws_security_group.allow-external1.id]
}

resource "aws_security_group" "allow-internal" {
  description = "Allows all traffic within the VPC"

  egress {
    cidr_blocks = ["0.0.0.0/0"]
    from_port   = "0"
    protocol    = "-1"
    self        = "false"
    to_port     = "0"
  }

  ingress {
    cidr_blocks = [data.aws_vpc.selected.cidr_block]
    from_port   = "80"
    protocol    = "tcp"
    self        = "false"
    to_port     = "80"
  }

  ingress {
    cidr_blocks = [data.aws_vpc.selected.cidr_block]
    from_port   = "443"
    protocol    = "tcp"
    self        = "false"
    to_port     = "443"
  }

  name   = "${var.project}-${var.environment}_allow-internal"
  vpc_id = data.aws_vpc.selected.id
}

resource "aws_security_group" "allow-external1" {
  description = "Allows external https traffic"

  egress {
    cidr_blocks = ["0.0.0.0/0"]
    from_port   = "0"
    protocol    = "-1"
    self        = "false"
    to_port     = "0"
  }

  ingress {
    cidr_blocks = ["0.0.0.0/0"]
    from_port   = "443"
    protocol    = "tcp"
    self        = "false"
    to_port     = "443"
  }

  ingress {
    cidr_blocks = ["0.0.0.0/0"]
    from_port   = "80"
    protocol    = "tcp"
    self        = "false"
    to_port     = "80"
  }

  name   = "${var.project}-${var.environment}_allow-external"
  vpc_id = data.aws_vpc.selected.id
}

resource "aws_lb_listener" "lb_listener" {
  load_balancer_arn = aws_lb.lb.arn
  port              = "80"
  protocol          = "HTTP"

  default_action {
   type             = "forward"
    target_group_arn = aws_lb_target_group.app.arn
  }
}

