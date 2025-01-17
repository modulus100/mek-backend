/*
 * This file was generated by the Gradle 'init' task.
 *
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    id("buildlogic.kotlin-library-conventions")
}

dependencies {
    api(enforcedPlatform(libs.spring.boot.dependencies))

    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.test)
    implementation(libs.spring.boot.security)
    implementation(libs.spring.boot.validation)
    implementation(libs.spring.boot.oauth2.client)
    implementation(libs.spring.boot.oauth2.resource.server)

    implementation(libs.jwt.api)
    implementation(libs.jwt.impl)
    implementation(libs.jwt.jackson)

    implementation(libs.postgresql.jdbc)
    implementation("org.apache.commons:commons-text")

    // For Java
    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
    testCompileOnly("org.projectlombok:lombok:1.18.34")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.34")

    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

    // For security
    implementation("org.bouncycastle:bcpkix-jdk18on:1.78.1")
}