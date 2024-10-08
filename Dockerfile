FROM gradle:8-jdk17-focal AS build

WORKDIR /app

COPY . .

RUN gradle clean build # --no-daemon

FROM openjdk:17-jdk-slim AS production

WORKDIR /app

COPY --from=build /app/build/libs/ArkGacha-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9000

ENTRYPOINT java -jar app.jar