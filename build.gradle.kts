plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation(libs.guava)
    implementation(libs.gson)
    implementation(libs.sqlite.jdbc)
    implementation(libs.lombok)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "io.github.mjaroslav.taskkeeper.TaskKeeper"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
