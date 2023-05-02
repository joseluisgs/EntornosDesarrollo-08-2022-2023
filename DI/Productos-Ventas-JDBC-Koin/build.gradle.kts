plugins {
    kotlin("jvm") version "1.8.0"
    application
    // Para Koin Annotation
    id("com.google.devtools.ksp") version "1.8.10-1.0.9"
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

    // Koin
    implementation("io.insert-koin:koin-core:3.4.0")
    implementation("io.insert-koin:koin-annotations:1.2.0") // Si usamos Koin con KSP Anotaciones
    ksp("io.insert-koin:koin-ksp-compiler:1.2.0") // Si usamos Koin con KSP Anotaciones
    implementation("io.insert-koin:koin-logger-slf4j:3.4.0") // Para el logger de Koin

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

// Para Koin Annotations, directorio donde se encuentran las clases compiladas
// KSP - To use generated sources
sourceSets.main {
    java.srcDirs("build/generated/ksp/main/kotlin")
}