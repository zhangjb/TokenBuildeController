"# TokenBuildeController" 


# 生成

docker build -t tokenbuilder_app .

# 启动

docker run -d -p 8888:8080 -e "app.cloudHome=/app/key" tokenbuilder_app

# 登录到容器

docker exec -it <container_id_or_name> /bin/bash

# 确保

/app/key/public.pem 存在
