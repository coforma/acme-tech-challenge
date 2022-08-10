resource "aws_ecs_cluster" "cluster" {
  name = var.name_prefix
  setting {
    name  = "containerInsights"
    value = "enabled"
  }
}

resource "aws_ecs_cluster_capacity_providers" "cluster" {
  cluster_name       = aws_ecs_cluster.cluster.name
  capacity_providers = ["FARGATE"]
}

resource "aws_ecs_service" "service" {
  name                               = var.name_prefix
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
    container_name   = var.name_prefix
    container_port   = var.port
    target_group_arn = aws_lb_target_group.app.arn
  }

  network_configuration {
    assign_public_ip = "true"
    security_groups  = [aws_security_group.allow-external.id]
    subnets          = var.vpc_public_subnets
  }


  depends_on = [
    aws_ecs_task_definition.app,
    aws_lb.lb
  ]
}

resource "aws_cloudwatch_log_group" "ecs_service_logs" {
  name = "${var.name_prefix}-app-logs"

  tags = {
    Project     = "${var.project}"
    Environment = "${var.environment}"
    Team        = "${var.team}"
  }
}

resource "aws_ecs_task_definition" "app" {
  execution_role_arn       = aws_iam_role.task.arn
  family                   = var.name_prefix
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  task_role_arn            = aws_iam_role.task.arn
  cpu                      = var.cpu
  memory                   = var.memory
  container_definitions = jsonencode([
    {
      name  = "${var.name_prefix}"
      image = "${var.app_image}"
      logConfiguration = {
        logDriver = "awslogs"
        options = {
          awslogs-group         = "${var.name_prefix}-app-logs",
          awslogs-region        = "us-east-1",
          awslogs-stream-prefix = "${var.project}",
          awslogs-create-group  = "true"
        }
      }
      portMappings = [
        {
          containerPort = var.port
          hostPort      = var.port
          protocol      = "tcp"
        }
      ]
      essential   = true
      environment = local.app_definitions
      secrets     = var.app_secrets
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
