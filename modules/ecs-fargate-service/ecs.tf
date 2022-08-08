resource "aws_ecs_cluster" "cluster" {
  name = "${var.project}-${var.environment}"
  setting {
    name  = "containerInsights"
    value = "enabled"
  }
  capacity_providers = ["FARGATE", ]
}

resource "aws_ecs_service" "service" {
  name                               = "${var.project}-${var.environment}"
  cluster                            = aws_ecs_cluster.cluster.id
  desired_count                      = var.replicas
  wait_for_steady_state              = true
  deployment_maximum_percent         = var.deployment_maximum_percent
  deployment_minimum_healthy_percent = var.deployment_minimum_healthy_percent
  enable_ecs_managed_tags            = "false"
  health_check_grace_period_seconds  = var.health_check_grace_period_seconds
  launch_type                        = "FARGATE"
  platform_version                   = var.platform_version
  scheduling_strategy                = "REPLICA"
  task_definition                    = aws_ecs_task_definition.app.arn
  enable_execute_command             = true

  deployment_controller {
    type = "ECS"
  }



  load_balancer {
    container_name   = "${var.project}-${var.environment}"
    container_port   = var.port
    target_group_arn = aws_lb_target_group.app.arn
  }

  network_configuration {
    assign_public_ip = "true"
    //security_groups  = ["sg-0d784290d6fd5c6a9"]
   security_groups  = [aws_security_group.allow-external.id]
    subnets          = data.aws_subnet_ids.public.ids
  }


  depends_on = [
    aws_ecs_task_definition.app,
    aws_lb.lb
  ]
}

resource "aws_ecs_task_definition" "app" {
  execution_role_arn       = aws_iam_role.task.arn
  family                   = "${var.project}-${var.environment}"
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  task_role_arn            = aws_iam_role.task.arn
  cpu                      = var.cpu
  memory                   = var.memory
  container_definitions = jsonencode([
    {
      name  = "${var.project}-${var.environment}"
      //image = "905975536748.dkr.ecr.us-east-1.amazonaws.com/acme-tech-challenge:${var.image_tag}"
      image = "905975536748.dkr.ecr.us-east-1.amazonaws.com/acme-tech-challenge:5a642f8"
      portMappings = [
        {
          containerPort = var.port
          hostPort      = var.port
          protocol      = "tcp"
        }
      ]
      essential = true
      environment : local.app_definitions
    }
    ]
  )
}

locals {
  app_definitions = flatten([
    for k, v in var.app_definitions : [
      {
        name  = k
        value = v
      }
    ]
  ])
}
