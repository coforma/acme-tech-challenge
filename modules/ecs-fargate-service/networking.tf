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
  name                          = "${var.project}-${var.environment}"
  port                          = var.port
  protocol                      = "HTTP"
  slow_start                    = "120"
  target_type                   = "ip"
  vpc_id                        = data.aws_vpc.selected.id

  lifecycle {
    create_before_destroy = true
  }
}

/*resource "aws_lb_listener_rule" "app" {
  listener_arn = aws_lb_listener.lb_listener.arn

  action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.app.arn
  }

  condition {
    path_pattern {
      values = [var.path]
    }
  }

  condition {
    host_header {
      values = ["test.abc.com"]
    }
  }
}
*/