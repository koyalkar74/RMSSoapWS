aws_region=ap-southeast-2
app_name=producer-app
aws_account_id=670748051181
app_version?=${BUILD_NUMBER}
stack_name=${app_name}-service-stack
template:=ecs-service.json
params:=ecs-service-params.json
AWS_PROFILE:=default

ifeq ($(BUILD_NUMBER),)
	app_version = latest
endif



all: package build_image login create_repo tag_repo push_image

package:
	mvn clean package

build_image:  login
	mvn docker:build

login:
	sh -c '`aws ecr get-login  --region ${aws_region}`'


tag_repo:
	docker tag ${app_name}  ${aws_account_id}.dkr.ecr.${aws_region}.amazonaws.com/${app_name}:${app_version}

push_image: tag_repo
	docker push ${aws_account_id}.dkr.ecr.${aws_region}.amazonaws.com/${app_name}:${app_version}

deploy_ecs_service:
	sed -e "s;%BUILD_NUMBER%;${app_version};g" ecs-service-params.json > /tmp/params_v_${app_name}_${app_version}.json &&\
	aws cloudformation update-stack --stack-name $(stack_name) --template-body file://`pwd`/$(template) --parameters file:///tmp/params_v_${app_name}_${app_version}.json --capabilities CAPABILITY_IAM  --region $(aws_region)

