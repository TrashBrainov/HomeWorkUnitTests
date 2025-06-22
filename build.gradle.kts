

plugins {
    id("java")
    id("io.qameta.allure") version "2.12.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("io.qameta.allure:allure-junit5:2.25.0")
    testImplementation("io.qameta.allure:allure-junit5")
    implementation("org.seleniumhq.selenium:selenium-java:4.18.1")
    testImplementation("org.assertj:assertj-core:3.25.3")
    implementation("commons-io:commons-io:2.16.0")
    implementation("org.apache.httpcomponents.client5:httpclient5:5.3.1")
    // https://mvnrepository.com/artifact/io.rest-assured/rest-assured
    testImplementation("io.rest-assured:rest-assured:5.5.5")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    implementation("io.rest-assured:json-schema-validator:5.4.0")
    implementation("io.qameta.allure:allure-rest-assured:2.27.0")
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")
    testCompileOnly("org.projectlombok:lombok:1.18.32")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.32")
    implementation("org.apache.pdfbox:pdfbox:2.0.31")
    implementation("com.codeborne:selenide:7.3.1")
    implementation("io.appium:java-client:8.5.1")
    implementation("io.github.bonigarcia:webdrivermanager:5.4.1")
    implementation("jakarta.persistence:jakarta.persistence-api:3.2.0-M2")
    implementation("com.h2database:h2:2.2.224")
    implementation("org.hibernate.orm:hibernate-core:6.5.2.Final")
    testImplementation("com.h2database:h2")
    implementation("org.springframework:spring-jdbc:5.3.23")
    testImplementation("org.springframework:spring-test:5.3.23")
}


tasks.test {
    useJUnitPlatform()
}