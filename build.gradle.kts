plugins {
	java
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// JPA
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	// MySQL Driver
	runtimeOnly("com.mysql:mysql-connector-j")

	// lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// swagger
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.6")

	// thymeleaf
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

	// webflux - 비동기 논블로킹 웹 프레임워크
	implementation("org.springframework.boot:spring-boot-starter-webflux:3.3.3")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
