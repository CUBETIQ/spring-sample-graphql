import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.3.3.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.spring") version "1.3.72"
    kotlin("plugin.jpa") version "1.3.72"
}

group = "com.cubetiqs.demo"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("com.graphql-java-kickstart:graphql-spring-boot-starter:7.0.1")

    // to embed Altair tool
    runtimeOnly("com.graphql-java-kickstart:altair-spring-boot-starter:7.0.1")

    // to embed GraphiQL tool
    runtimeOnly("com.graphql-java-kickstart:graphiql-spring-boot-starter:7.0.1")

    // to embed Voyager tool
    runtimeOnly("com.graphql-java-kickstart:voyager-spring-boot-starter:7.0.1")

    runtimeOnly("org.postgresql:postgresql")

    // testing facilities
    testImplementation("com.graphql-java-kickstart:graphql-spring-boot-starter-test:7.0.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}
