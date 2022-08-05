/*module "vpc" {
  source = "terraform-aws-modules/vpc/aws"

  name = "${var.project}-vpc"
  cidr = "10.0.0.0/16"

  azs             = ["us-east-2a", "us-east-2b"]
  public_subnets  = ["10.0.101.0/24", "10.0.102.0/24"]

}
 */
 module "ecs-fargate-service" {
  // depends_on                = [module.vpc]
   source                  = "./modules/ecs-fargate-service"
   vpc_id                  = var.vpc_id
   environment             = var.environment
   project                 = var.project
   region                  = var.region
   app_definitions         = local.app_definitions
   health_check_path       = "/swagger-ui/index.html"
 }
 

 
