import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.gradle.api.tasks.testing.Test

plugins {
    id("org.springframework.boot") version "4.0.1"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("jvm") version "2.3.0"
    kotlin("plugin.spring") version "2.3.0"
    id("org.flywaydb.flyway") version "11.20.2"
}

group = "{{ group_name }}"
version = "0.0.1-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.21.0")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.3.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect:2.3.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.10.2")
    implementation("io.arrow-kt:arrow-core:2.2.1.1")
    implementation("com.zaxxer:HikariCP:7.0.2")
    implementation("org.postgresql:postgresql:42.7.9")
    implementation("org.flywaydb:flyway-core:11.20.2")
    implementation("org.flywaydb:flyway-database-postgresql:11.20.2")
    implementation(platform("org.apache.logging.log4j:log4j-bom:2.25.3"))

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "mockito-core")
        exclude(group = "junit", module = "junit")
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("org.springframework.boot:spring-boot-starter-webflux-test")
    testImplementation("io.projectreactor:reactor-test:3.8.2")
    testImplementation("org.testcontainers:testcontainers:2.0.3")
    testImplementation("org.testcontainers:junit-jupiter:1.21.4")
    testImplementation("org.testcontainers:postgresql:1.21.4")
    testImplementation("io.kotest:kotest-assertions-core-jvm:6.1.0")
    testImplementation("io.kotest:kotest-assertions-json:6.1.0")
    testImplementation("io.kotest:kotest-runner-junit5:6.1.0")
    testImplementation("io.rest-assured:rest-assured:6.0.0")
    testImplementation("io.rest-assured:json-path:6.0.0")
    testImplementation("io.rest-assured:xml-path:6.0.0")
    testImplementation("io.rest-assured:json-schema-validator:6.0.0")
    testImplementation("io.mockk:mockk:1.14.7")
    testImplementation("com.ninja-squad:springmockk:5.0.1")
    testImplementation("com.tngtech.archunit:archunit:1.4.1")
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        // was: freeCompilerArgs = listOf("-Xjsr305=strict")
        freeCompilerArgs.add("-Xjsr305=strict")

        // was: jvmTarget = "21"
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

val unitTest = tasks.register<Test>("unitTest") {
    description = "Runs unit tests."
    useJUnitPlatform {
        excludeTags("integration", "component")
    }
    shouldRunAfter(tasks.test)
}

val integrationTest = tasks.register<Test>("integrationTest") {
    description = "Runs integration tests."
    useJUnitPlatform {
        includeTags("integration")
    }
    shouldRunAfter(tasks.test)
}

val componentTest = tasks.register<Test>("componentTest") {
    description = "Runs component tests."
    useJUnitPlatform {
        includeTags("component")
    }
    shouldRunAfter(tasks.test)
}

tasks.named("check") {
    dependsOn(unitTest, integrationTest, componentTest)
}
