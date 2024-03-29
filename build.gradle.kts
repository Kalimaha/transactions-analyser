import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.3.21"
    application
}

group = "au.com.mebank"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    testImplementation("org.junit.jupiter", "junit-jupiter", "5.4.2")
    testImplementation("org.assertj", "assertj-core", "3.11.1")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

application {
    mainClassName = "au.com.mebank.transactions.analyser.Main"
}