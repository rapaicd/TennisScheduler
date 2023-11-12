FROM maven:3.8.5-openjdk-17-slim AS unsAppBuild
WORKDIR /root/src/backend
COPY . .
RUN mvn package -DskipTests

FROM openjdk:17 AS unsAppRuntime
WORKDIR /app
COPY --from=unsAppBuild /root/src/backend/target/UserService.jar ./
EXPOSE 8082
CMD java -jar UserService.jar