plugins {
    kotlin("jvm") version "1.8.0"
    application
    // Plugin KSP para Koin Annotation, debe coicidir con la de Kotlin
    id("com.google.devtools.ksp") version "1.8.0-1.0.9"
}

group = "dev.joseluisgs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Logger
    implementation("ch.qos.logback:logback-classic:1.4.5")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.4")

    // Koin
    implementation("io.insert-koin:koin-core:3.4.0") // Obligatorio
    implementation("io.insert-koin:koin-logger-slf4j:3.4.0") // Logger para verlo
    // Koin Annotation
    implementation("io.insert-koin:koin-annotations:1.2.0")
    ksp("io.insert-koin:koin-ksp-compiler:1.2.0")


    // Test
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}

// Para Koin Annotations, directorio donde se encuentran las clases compiladas
// No es obligatorio, pero es recomendable para controlarlo
// KSP - To use generated sources
sourceSets.main {
    java.srcDirs("build/generated/ksp/main/kotlin")
}