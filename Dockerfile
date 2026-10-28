# To update: docker buildx imagetools inspect dhi.io/eclipse-temurin:25-jdk-alpine3.24-dev
ARG BUILD_IMAGE=ghcr.io/kartverket/emudhra-images/jdk/dockerhub:jdk-25.0-alpine3.24@sha256:9040fd4372ac7c4581712c77ba9324a175ff9f9679ab3ff60ea5c12eb02fcdb9
# To update: docker buildx imagetools inspect ghcr.io/kartverket/emudhra-images/jre/dhi:jre-25.0-alpine3.24
ARG IMAGE=ghcr.io/kartverket/emudhra-images/jre/dhi:jre-25.0-alpine3.24@sha256:0560e1680f602893991ce298a03975ebf3cc467fc0b01275ed1d83d03e013044

FROM ${BUILD_IMAGE} AS build
WORKDIR /src
COPY . .
RUN ./gradlew bootJar

FROM --platform=linux/amd64 ${IMAGE}

EXPOSE 8080 8081
WORKDIR /app

COPY --from=build /src/build/libs/*.jar ./app.jar
USER nonroot
ENTRYPOINT ["java","-jar","app.jar"]