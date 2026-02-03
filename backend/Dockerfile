FROM gradle:8.3-jdk17 AS build

WORKDIR /app

COPY . .

RUN chmod +x gradlew

RUN ./gradlew build -x test --no-daemon

EXPOSE 8443

CMD ["./gradlew","bootRun"]