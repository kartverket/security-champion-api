# To update: docker buildx imagetools inspect dhi.io/eclipse-temurin:25-jdk-alpine3.24-dev
ARG BUILD_IMAGE=ghcr.io/kartverket/emudhra-images/jdk/dockerhub:jdk-25.0-alpine3.24@sha256:3b9c9aa8f9cd11b2660bace83082b223abc6923a9fc54bb7e08c9f122565db72
# To update: docker buildx imagetools inspect ghcr.io/kartverket/emudhra-images/jre/dhi:jre-25.0-alpine3.24
ARG IMAGE=ghcr.io/kartverket/emudhra-images/jre/dhi:jre-25.0-alpine3.24@sha256:f0b12aab0f96059700032dada95e0bf85e8e0e7404d92246370a0a752ed21c4d

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