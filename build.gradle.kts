val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val exposed_version: String by project
val h2_version: String by project


plugins {
    application
    kotlin("jvm") version "1.7.10"
    id("io.ktor.plugin") version "2.1.0"
                id("org.jetbrains.kotlin.plugin.serialization") version "1.7.10"
}

group = "com.fight"
version = "0.0.1"
application {
    mainClass.set("com.fight.ApplicationKt")

//    val isDevelopment: Boolean = project.ext.has("development")
    val isDevelopment = true
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")


}

repositories {
    mavenCentral()
}

dependencies {
    // Ktor server
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-default-headers-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-cors-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-compression-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-conditional-headers-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-caching-headers-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")

    // Database
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("com.h2database:h2:$h2_version")

    implementation("io.ktor:ktor-server-freemarker:$ktor_version")

    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}