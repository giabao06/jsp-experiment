plugins {
    id("java")
    id ("org.springframework.boot") version "3.4.1"
}

apply(plugin = "io.spring.dependency-management")

group = "xyz.giabao06"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.springframework.boot:spring-boot-starter-web")
}

tasks.test {
    useJUnitPlatform()
}
