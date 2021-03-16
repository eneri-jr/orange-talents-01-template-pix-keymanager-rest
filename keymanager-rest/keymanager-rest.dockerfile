# Rodando a imagem
FROM adoptopenjdk/openjdk11:alpine
ARG JAR_FILE=build/libs/*all.jar
COPY ${JAR_FILE} keymanager-rest-0.1-runner.jar
ENTRYPOINT ["java","-jar","/keymanager-rest-0.1-runner.jar"]
