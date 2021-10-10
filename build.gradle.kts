import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.5"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.31"
	kotlin("plugin.spring") version "1.5.31"
}

group = "[[ group_name ]]"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("io.arrow-kt:arrow-core:1.0.0")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(module = "mockito-core")
	}
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.testcontainers:testcontainers:1.16.0")
	testImplementation("org.testcontainers:junit-jupiter:1.16.0")
	testImplementation("org.testcontainers:postgresql:1.16.0")
	testImplementation("io.kotest:kotest-assertions-core-jvm:4.6.3")
	testImplementation("io.kotest:kotest-assertions-json:4.6.3")
	testImplementation("io.kotest:kotest-runner-junit5:4.6.3")
	testImplementation("io.rest-assured:rest-assured:4.4.0")
	testImplementation("io.mockk:mockk:1.12.0")
	testImplementation("com.ninja-squad:springmockk:3.0.1")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
