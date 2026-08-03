# To update: docker buildx imagetools inspect dhi.io/eclipse-temurin:25-jdk-alpine3.24-dev
ARG BUILD_IMAGE=dhi.io/eclipse-temurin:25-jdk-alpine3.24-dev@sha256:8f1e944fe65a7120dedf30a0754c743f211701babced9625b9ed5c9cddb52b44
# To update: docker buildx imagetools inspect dhi.io/eclipse-temurin:25-alpine3.24
ARG IMAGE=dhi.io/eclipse-temurin:25-alpine3.24@sha256:cbd182208579d29ec12007e7c570e72b57eff5353c69f6fa86352bf65469a222

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