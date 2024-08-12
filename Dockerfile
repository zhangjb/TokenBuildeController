# 使用官方的 OpenJDK 22 镜像作为基础镜像进行构建
FROM openjdk:22-jdk-slim AS build

# 设置工作目录
WORKDIR /app

# 将 Maven 项目的 `pom.xml` 文件复制到容器中
COPY pom.xml .

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

# 运行 Java 应用程序
ENTRYPOINT ["java", "-jar", "my-java-app.jar"]
