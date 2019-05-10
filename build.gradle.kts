plugins {
    idea
    application
    id("com.github.johnrengelman.shadow") version "5.0.0"
    kotlin("jvm") version "1.3.31"
}

group = "me.camdenorrb"
version = "1.0.0"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {

    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))

    implementation("org.processing:core:+")
    implementation("me.camdenorrb:MiniBus:+")

    implementation(files("libs/gifAnimation.jar"))
}

application {
    mainClassName = "me.camdenorrb.zambiegame.Main"
}