FROM openjdk:17-alpine AS extraction
WORKDIR workspace
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
RUN java -Djarmode=layertools -jar app.jar extract
# setup image
FROM openjdk:17-alpine AS image
RUN addgroup -S appgroup && adduser -S spring -G appgroup
USER spring
WORKDIR workspace
COPY --from=extraction workspace/dependencies/ ./
COPY --from=extraction workspace/spring-boot-loader/ ./
COPY --from=extraction workspace/snapshot-dependencies/ ./
COPY --from=extraction workspace/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
