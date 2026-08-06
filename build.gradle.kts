plugins {
    kotlin("jvm") version "2.3.21"
    kotlin("plugin.spring") version "2.3.21"
    id("org.springframework.boot") version "4.0.6"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.jlleitschuh.gradle.ktlint") version "13.1.0"
}

val otelInstrumentationVersion = "2.19.0"
val postgresVersion = "42.7.13"

ktlint {
    version.set("1.6.0")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "security-champion-api"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("io.opentelemetry.instrumentation:opentelemetry-instrumentation-bom:$otelInstrumentationVersion")
        mavenBom("com.fasterxml.jackson:jackson-bom:2.22.1") // Fixes CVE-2026-54513
        mavenBom("tools.jackson:jackson-bom:3.1.5") // Security fix
    }
}

ext["tomcat.version"] = "11.0.24"
ext["spring-framework.version"] = "7.0.8" // security fix

configurations.all {
    resolutionStrategy.eachDependency {
        if (requested.group == "org.springframework.data" && requested.name == "spring-data-commons") {
            useVersion("4.0.6")
            because("Security fix: upgrade from 4.0.5 to 4.0.6")
        }
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("io.opentelemetry.instrumentation:opentelemetry-spring-boot-starter")
    implementation("net.logstash.logback:logstash-logback-encoder:8.1")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    implementation("org.postgresql:postgresql:${postgresVersion}")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-postgresql")
    implementation("io.micrometer:micrometer-registry-prometheus")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_25)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
