resource "aws_lb_target_group" "app" {
  deregistration_delay = "30"

  health_check {
    enabled             = "true"
    healthy_threshold   = "2"
    interval            = "10"
    matcher             = "200"
    path                = var.health_check_path
    port                = "traffic-port"
    protocol            = "HTTP"
    timeout             = "2"
    unhealthy_threshold = "2"
  }

  load_balancing_algorithm_type = "round_robin"
  name                          = substr(var.name_prefix, 0, 32)
  port                          = var.port
  protocol                      = "HTTP"
  slow_start                    = "120"
  target_type                   = "ip"
  vpc_id                        = var.vpc_id

  lifecycle {
    create_before_destroy = true
  }
}
