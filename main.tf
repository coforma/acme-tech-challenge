 module "ecs-fargate-service" {
  // depends_on                = [module.vpc]
   source                  = "./modules/ecs-fargate-service"
   vpc_id                  = var.vpc_id
   environment             = var.environment
   project                 = var.project
   image_tag               = var.image_tag
   region                  = var.region
   app_definitions         = local.app_definitions
   health_check_path       = "/swagger-ui/index.html"
 }
 

 
