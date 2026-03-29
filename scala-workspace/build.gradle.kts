// vim: filetype=kotlin: tabstop=3: shiftwidth=3: noexpandtab
plugins {
	scala
	application
	id("org.openjfx.javafxplugin") version "0.1.0"
}

repositories {
	mavenCentral()
}

val scalaVersion = "3.3.6"
val scalaFxVersion = "21.0.0-R32"
val scalaTestVersion = "3.2.19"

javafx {
	version = "21.0.2"
	modules(
		"javafx.controls",
		"javafx.fxml",
		"javafx.media"
	)
}

dependencies {
	implementation("org.scala-lang:scala3-library_3:$scalaVersion")
	implementation("org.scalafx:scalafx_3:$scalaFxVersion")

	testImplementation("org.scalatest:scalatest_3:$scalaTestVersion")
	testRuntimeOnly("org.junit.platform:junit-platform-engine:1.10.2")
	testRuntimeOnly("org.scalatestplus:junit-5-10_3:3.2.19.0")
}

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(21))
	}
}

application {
	mainClass.set("app.Main")
}

tasks.withType<ScalaCompile>().configureEach {
	scalaCompileOptions.additionalParameters = listOf(
		"-feature"
	)
}

tasks.named<Test>("test") {
	useJUnitPlatform()
}
