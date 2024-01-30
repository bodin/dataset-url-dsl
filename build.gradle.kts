plugins {
    `java-library`
    `antlr`
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
    testImplementation("org.antlr:antlr4:4.13.1")

    // Use JUnit Jupiter for testing.
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")

    // This dependency is exported to consumers, that is to say found on their compile classpath.
    api("org.apache.commons:commons-math3:3.6.1")

    // This dependency is used internally, and not exposed to consumers on their own compile classpath.
    implementation("com.google.guava:guava:30.1.1-jre")
}

tasks.test {
    useJUnitPlatform()
}
