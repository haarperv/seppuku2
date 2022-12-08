plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))

    implementation(project(":seppuku-sdk:repository"))

    testImplementation(kotlin("test"))
}

tasks {
    test {
        useJUnitPlatform()
    }
}