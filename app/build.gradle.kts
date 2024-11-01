/*
 * This file was generated by the Gradle 'init' task.
 *
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    id("buildlogic.kotlin-application-conventions")
}

dependencies {
    api(enforcedPlatform(libs.spring.boot.dependencies))

    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.test)
    implementation(libs.spring.boot.starter.data.jpa)

    implementation(project(":auth"))
    implementation(project(":iam"))
    api(project(":service-api"))
}

application {
    mainClass = "mek.backend.app.ServiceApiAppKt"
}

springBoot {
    mainClass = "mek.backend.app.ServiceApiAppKt"
}

