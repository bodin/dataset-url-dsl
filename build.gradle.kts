plugins {
    `java-library`
    `antlr`
    kotlin("jvm") version "1.9.22"
    id("me.champeau.jmh") version "0.7.2"
}

repositories {
    mavenCentral()
}
tasks.wrapper{
    version = "8.5"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}
tasks.generateGrammarSource {
    arguments = arguments + listOf("-visitor")
}

dependencies {
    antlr("org.antlr:antlr4:4.13.1")

    //testImplementation ("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation ("io.kotest:kotest-runner-junit5:5.8.0")
    testImplementation ("io.kotest:kotest-assertions-core:5.8.0")
    testImplementation ("io.kotest:kotest-property:5.8.0")
}

tasks.test {
    useJUnitPlatform()
}
