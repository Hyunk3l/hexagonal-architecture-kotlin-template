import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.5.3"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("jvm") version "2.1.21"
    kotlin("plugin.spring") version "2.1.21"
    id("org.flywaydb.flyway") version "11.10.2"
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
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.19.1")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.2.3")
    implementation("org.jetbrains.kotlin:kotlin-reflect:2.2.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.2.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.10.2")
    implementation("io.arrow-kt:arrow-core:2.1.2")
    implementation("com.zaxxer:HikariCP:6.3.0")
    implementation("org.postgresql:postgresql:42.7.7")
    implementation("org.flywaydb:flyway-core:11.10.2")
    implementation("org.flywaydb:flyway-database-postgresql:11.10.2")
    implementation(platform("org.apache.logging.log4j:log4j-bom:2.25.0"))

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "mockito-core")
        exclude(group = "junit", module = "junit")
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("io.projectreactor:reactor-test:3.7.7")
    testImplementation("org.testcontainers:testcontainers:1.21.3")
    testImplementation("org.testcontainers:junit-jupiter:1.21.3")
    testImplementation("org.testcontainers:postgresql:1.21.3")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.9.1")
    testImplementation("io.kotest:kotest-assertions-json:5.9.1")
    testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
    testImplementation("io.rest-assured:rest-assured:5.5.5")
    testImplementation("io.rest-assured:json-path:5.5.5")
    testImplementation("io.rest-assured:xml-path:5.5.5")
    testImplementation("io.rest-assured:json-schema-validator:5.5.5")
    testImplementation("io.mockk:mockk:1.14.4")
    testImplementation("com.ninja-squad:springmockk:4.0.2")
    testImplementation("com.tngtech.archunit:archunit:1.4.1")
}

buildscript {
    dependencies {
        classpath("org.flywaydb:flyway-database-postgresql:11.10.2")
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
