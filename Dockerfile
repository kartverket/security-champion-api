ARG BUILD_IMAGE=eclipse-temurin:24.0.2_12-jdk-alpine-3.22
ARG IMAGE=eclipse-temurin:24.0.2_12-jre-alpine-3.22

FROM ${BUILD_IMAGE} AS build
COPY . .
RUN ./gradlew build -x test

FROM ${IMAGE}
# Update package index and upgrade libexpat to a specific version
RUN apk upgrade --no-cache # && apk add --no-cache libexpat=2.7.0-r0

RUN mkdir /app
EXPOSE 8080 8081
RUN adduser -D user && chown -R user /app
WORKDIR /app
COPY --from=build /build/libs/security-champion-api-0.0.1-SNAPSHOT.jar ./app.jar


USER user
ENTRYPOINT ["java","-jar","app.jar"]

# Use the health endpoint of the application to provide information through docker about the health state of the application
HEALTHCHECK --start-period=30s --interval=5m \
   CMD wget -O - --quiet --tries=1 http://localhost:8081/actuator/health | grep UP || exit 1

