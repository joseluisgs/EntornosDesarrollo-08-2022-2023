plugins {
    kotlin("jvm") version "1.8.0"
    application
    // Para Dagger
    kotlin("kapt") version "1.8.0"
}

group = "es.joseluisgs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Logger
    implementation("ch.qos.logback:logback-classic:1.4.5")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.4")

    // Moshi
    implementation("com.squareup.moshi:moshi:1.14.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    implementation("com.squareup.moshi:moshi-adapters:1.14.0")

    // SQLite
    implementation("org.xerial:sqlite-jdbc:3.41.2.1")

    // H2
    // implementation("com.h2database:h2:2.1.214")

    // MyBatis para scripts SQL y otras utilidades
    implementation("org.mybatis:mybatis:3.5.11")

    // Dagger
    implementation("com.google.dagger:dagger:2.45")
    kapt("com.google.dagger:dagger-compiler:2.45")

    // Test con JUnit 5 básico
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}
