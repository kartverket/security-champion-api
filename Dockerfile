# To update: docker buildx imagetools inspect dhi.io/eclipse-temurin:25-jdk-alpine3.24-dev
ARG BUILD_IMAGE=dhi.io/eclipse-temurin:25-jdk-alpine3.24-dev@sha256:e4bcb21502dcd0d8e7f5e9a30cb6fca24c02aaae58137853a9869fba0a5b6570
# To update: docker buildx imagetools inspect dhi.io/eclipse-temurin:25-alpine3.24
ARG IMAGE=dhi.io/eclipse-temurin:25-alpine3.24@sha256:7f6fe5ca802ecb2e6de576a47813213949b11a1116030e57151b6c8b94adfa26

FROM ${BUILD_IMAGE} AS build
WORKDIR /src
COPY . .
RUN ./gradlew bootJar

FROM ${IMAGE}

EXPOSE 8080 8081
WORKDIR /app

COPY --from=build /src/build/libs/*.jar ./app.jar
USER nonroot
ENTRYPOINT ["java","-jar","app.jar"]