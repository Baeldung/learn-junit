plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ch.qos.logback.logback.classic)
    testImplementation(libs.org.junit.jupiter.junit.jupiter)
    testImplementation(libs.org.mockito.mockito.core)
    testImplementation(libs.org.mockito.mockito.junit.jupiter)
    testRuntimeOnly(libs.org.junit.platform.junit.platform.launcher)
}

group = "com.baeldung"
version = "1.0.0"
description = "junit-assertions-end"
java.sourceCompatibility = JavaVersion.VERSION_21

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.test {
    useJUnitPlatform()
}
