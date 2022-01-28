plugins {
    scala
    id("fabric-loom") version "0.10-SNAPSHOT"
}
base {
    val archivesBaseName: String by project
    archivesName.set(archivesBaseName)
}

val javaVersion = JavaVersion.VERSION_17.toString()
val modVersion: String by project
val mavenGroup: String by project
val minecraftVersion: String by project
val yarnMappings: String by project
val loaderVersion: String by project
val fabricVersion: String by project

version = modVersion
group = mavenGroup

minecraft {}
repositories {
    mavenCentral()
}

dependencies {

    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings("net.fabricmc:yarn:$yarnMappings:v2")
    modImplementation("net.fabricmc:fabric-loader:$loaderVersion")

    // FAPI
    modImplementation("net.fabricmc.fabric-api:fabric-api:$fabricVersion")

    // Scala
    implementation("org.scala-lang:scala3-library_3:3.1.1")
    include("org.scala-lang:scala3-library_3:3.1.1")

    implementation("commons-collections:commons-collections:3.2.2")
    include("commons-collections:commons-collections:3.2.2")
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        targetCompatibility = javaVersion
        sourceCompatibility = javaVersion
        options.release.set(javaVersion.toInt())
    }
    withType<ScalaCompile> {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
    jar { from("LICENSE") { rename { "${it}_${base.archivesName}" } } }
    processResources {
        inputs.property("version", project.version)
        filesMatching("fabric.mod.json") { expand("version" to project.version) }
    }
    java {
        toolchain { languageVersion.set(JavaLanguageVersion.of(javaVersion)) }
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        withSourcesJar()
    }
}

