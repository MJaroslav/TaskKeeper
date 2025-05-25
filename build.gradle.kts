plugins {
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
    idea
}

repositories {
    mavenCentral()
    maven {
        name = "JitPack"
        url = uri("https://jitpack.io/")
    }
}

dependencies {
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation(libs.guava)
    implementation(libs.gson)
    implementation(libs.sqlite.jdbc)
    implementation(libs.shared.java)
    implementation(libs.log4j.api)
    implementation(libs.log4j.core)
    implementation(libs.atlantafx.base)
    implementation(libs.appdirs)
    implementation(libs.sqlite.java)
    implementation(libs.fx.gson)

    compileOnly(libs.jetbrains.annotations)

    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    testCompileOnly(libs.lombok)
    testAnnotationProcessor(libs.lombok)
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "io.github.mjaroslav.taskkeeper.TaskKeeper"
}

javafx {
    version = "21.0.2"
    modules("javafx.controls", "javafx.fxml")
    configuration = "implementation"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
