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

//    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.test)
    implementation(libs.spring.boot.security)
    implementation(libs.spring.boot.oauth2.client)
    implementation(libs.spring.boot.oauth2.resource.server)

    implementation(libs.jwt.api)
    implementation(libs.jwt.impl)
    implementation(libs.jwt.jackson)

    implementation("org.apache.commons:commons-text")
//    implementation(project(":utilities"))
}