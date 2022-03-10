import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("io.gitlab.arturbosch.detekt") version "1.19.0"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
}

group = "[[ group_name ]]"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux:2.6.4")
    implementation("org.springframework.boot:spring-boot-starter-jdbc:2.6.4")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.1")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.5")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.0-native-mt")
    implementation("io.arrow-kt:arrow-core:1.0.1")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("org.postgresql:postgresql:42.3.3")
    implementation("org.flywaydb:flyway-core:8.5.2")
    implementation(platform("org.apache.logging.log4j:log4j-bom:2.17.0"))

    testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.4") {
        exclude(module = "mockito-core")
        exclude(group = "junit", module = "junit")
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("io.projectreactor:reactor-test:3.4.15")
    testImplementation("org.testcontainers:testcontainers:1.16.3")
    testImplementation("org.testcontainers:junit-jupiter:1.16.3")
    testImplementation("org.testcontainers:postgresql:1.16.3")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.1.0")
    testImplementation("io.kotest:kotest-assertions-json:5.1.0")
    testImplementation("io.kotest:kotest-runner-junit5:5.1.0")
    testImplementation("io.rest-assured:rest-assured:4.5.1")
    testImplementation("io.rest-assured:json-path:4.5.1")
    testImplementation("io.rest-assured:xml-path:4.5.1")
    testImplementation("io.rest-assured:json-schema-validator:4.5.1")
    testImplementation("io.mockk:mockk:1.12.3")
    testImplementation("com.ninja-squad:springmockk:3.1.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks {
    withType<Test> {
        useJUnitPlatform()
    }

    task<Test>("unitTest") {
        useJUnitPlatform {
            excludeTags("integration")
            excludeTags("component")
        }
        shouldRunAfter(test)
    }

    task<Test>("integrationTest") {
        description = "Runs integration tests."
        useJUnitPlatform {
            includeTags("integration")
        }
        shouldRunAfter(test)
    }

    task<Test>("componentTest") {
        useJUnitPlatform {
            includeTags("component")
        }
        shouldRunAfter(test)
    }
}
