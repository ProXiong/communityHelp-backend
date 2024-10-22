# Docker 镜像构建
# 这个镜像包含了maven和JDK和Alpine Linux是一个轻量级的Linux发行版,可以减少镜像大小。
FROM maven:3.5-jdk-8-alpine as builder

# Copy local code to the container image.
# 后面都是在容器中执行的命令，所以需要复制本地代码到镜像中
WORKDIR /app
# 这里的根目录是 /app .
COPY pom.xml .
COPY src ./src

# Build a release artifact.
RUN mvn package -DskipTests

# Run the web service on container startup.
# CMD ["java","-jar","/app/target/user-center-backend-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]
ENTRYPOINT["java", "-jar", "/app/target/CH-userCenter-0.0.1.jar"]