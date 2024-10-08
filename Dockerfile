FROM gradle:8-jdk17-focal

WORKDIR /app

COPY . .

RUN gradle clean build

COPY build/libs/*.jar app.jar

EXPOSE 9000

ENTRYPOINT java -jar app.jar