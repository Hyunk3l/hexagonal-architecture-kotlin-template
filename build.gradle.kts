import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("jvm") version "2.0.10"
    kotlin("plugin.spring") version "2.0.10"
    id("org.flywaydb.flyway") version "10.17.1"
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
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.2")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.2.2")
    implementation("org.jetbrains.kotlin:kotlin-reflect:2.0.10")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.0.10")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.8.1")
    implementation("io.arrow-kt:arrow-core:1.2.4")
    implementation("com.zaxxer:HikariCP:5.1.0")
    implementation("org.postgresql:postgresql:42.7.3")
    implementation("org.flywaydb:flyway-core:10.17.0")
    implementation("org.flywaydb:flyway-database-postgresql:10.17.0")
    implementation(platform("org.apache.logging.log4j:log4j-bom:2.23.1"))

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "mockito-core")
        exclude(group = "junit", module = "junit")
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("io.projectreactor:reactor-test:3.6.8")
    testImplementation("org.testcontainers:testcontainers:1.20.1")
    testImplementation("org.testcontainers:junit-jupiter:1.20.1")
    testImplementation("org.testcontainers:postgresql:1.20.1")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.9.1")
    testImplementation("io.kotest:kotest-assertions-json:5.9.1")
    testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
    testImplementation("io.rest-assured:rest-assured:5.5.0")
    testImplementation("io.rest-assured:json-path:5.5.0")
    testImplementation("io.rest-assured:xml-path:5.5.0")
    testImplementation("io.rest-assured:json-schema-validator:5.5.0")
    testImplementation("io.mockk:mockk:1.13.12")
    testImplementation("com.ninja-squad:springmockk:4.0.2")
    testImplementation("com.tngtech.archunit:archunit:1.3.0")
}

buildscript {
    dependencies {
        classpath("org.flywaydb:flyway-database-postgresql:10.17.0")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "21"
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
