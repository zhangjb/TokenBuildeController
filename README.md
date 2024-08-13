"# TokenBuildeController" 


# 生成

docker build -t tokenbuilder_app .

# 启动

docker run -d -p 9800:8080 -e "app.cloudHome=/app/key" tokenbuilder_app

# 登录到容器

docker exec -it  tokenbuilder_app  /bin/bash

# 确保

/app/key/public.pem 存在

# 命令行参数启动

java -jar TokenBuilder-0.0.1-SNAPSHOT.jar --server.port=9800 --app.cloudHome=/home/client/downloads/tokenbuilder-app/key
