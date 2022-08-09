data "aws_iam_policy_document" "log_policy" {
  statement {
    actions = [
      "logs:CreateLogGroup",
    ]
    effect = "Allow"
    resources = ["arn:aws:logs:*:*:*"]
  }
}

resource "aws_iam_role" "task" {
  name                     = "${var.project}-${var.environment}-task-role"
    assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action = "sts:AssumeRole"
        Effect = "Allow"
        Sid    = ""
        Principal = {
          Service = "ecs-tasks.amazonaws.com"
        }
      }
    ]
  })

  inline_policy {
    policy = data.aws_iam_policy_document.log_policy.json
  }
  
}

resource "aws_iam_role_policy_attachment" "task" {
  role       = aws_iam_role.task.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AmazonECSTaskExecutionRolePolicy"
}

