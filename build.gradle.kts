import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    id("io.gitlab.arturbosch.detekt") version "1.23.1"
    kotlin("jvm") version "1.9.10"
    kotlin("plugin.spring") version "1.9.10"
}

group = "{{ group_name }}"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.3")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.2.2")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.20")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.20")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.7.3")
    implementation("io.arrow-kt:arrow-core:1.2.1")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("org.postgresql:postgresql:42.6.0")
    implementation("org.flywaydb:flyway-core:9.22.3")
    implementation(platform("org.apache.logging.log4j:log4j-bom:2.21.1"))

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "mockito-core")
        exclude(group = "junit", module = "junit")
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("io.projectreactor:reactor-test:3.5.11")
    testImplementation("org.testcontainers:testcontainers:1.19.1")
    testImplementation("org.testcontainers:junit-jupiter:1.19.1")
    testImplementation("org.testcontainers:postgresql:1.19.1")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.7.2")
    testImplementation("io.kotest:kotest-assertions-json:5.7.2")
    testImplementation("io.kotest:kotest-runner-junit5:5.7.2")
    testImplementation("io.rest-assured:rest-assured:5.3.2")
    testImplementation("io.rest-assured:json-path:5.3.2")
    testImplementation("io.rest-assured:xml-path:5.3.2")
    testImplementation("io.rest-assured:json-schema-validator:5.3.2")
    testImplementation("io.mockk:mockk:1.13.8")
    testImplementation("com.ninja-squad:springmockk:4.0.2")
    testImplementation("com.tngtech.archunit:archunit:1.1.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

// Temporary fix for the incompatibility of Detekt with Kotlin 1.9.10
configurations.detekt {
    resolutionStrategy.eachDependency {
        if (requested.group == "org.jetbrains.kotlin") {
            // See https://github.com/detekt/detekt/issues/6198#issuecomment-1700332653
            // and https://github.com/detekt/detekt/issues/6428
            useVersion("1.9.0")
        }
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
