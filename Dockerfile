# 使用官方的 OpenJDK 22 镜像作为基础镜像进行构建
FROM openjdk:22-jdk-slim AS build

# 安装 Maven
RUN apt-get update && apt-get install -y maven

# 设置工作目录
WORKDIR /app

# 复制 Maven 项目的 pom.xml 文件到工作目录
COPY pom.xml /app/

# 复制本地的 settings.xml 文件到 Maven 配置目录中
COPY settings.xml /root/.m2/settings.xml

# 下载依赖项
RUN mvn dependency:go-offline -B

# 将项目的源代码复制到容器中
COPY src ./src


# 构建应用程序
RUN mvn clean package -DskipTests

# 使用官方的 OpenJDK 镜像作为运行环境
FROM openjdk:22-jdk-slim

# 设置工作目录
WORKDIR /app

# 从构建阶段复制构建的 JAR 文件
COPY --from=build /app/target/TokenBuilder-0.0.1-SNAPSHOT.jar /app/TokenBuilder-0.0.1-SNAPSHOT.jar
COPY key /app/key
# 运行 Java 应用程序
ENTRYPOINT ["java", "-jar", "TokenBuilder-0.0.1-SNAPSHOT.jar"]
