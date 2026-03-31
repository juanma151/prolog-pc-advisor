// vim: filetype=kotlin: tabstop=3: shiftwidth=3: noexpandtab
plugins {
	scala
	application
	id("org.openjfx.javafxplugin") version "0.1.0"
	id("org.beryx.runtime") version "2.0.1"
}

group = "org.hagane"
version = "1.0.0"

repositories {
	mavenCentral()
}

val scalaVersion = "3.3.6"
val scalaFxVersion = "21.0.0-R32"
val scalaTestVersion = "3.2.19"

val jplJarPath = System.getenv("JPL_JAR")
	?: error("Environment variable JPL_JAR is not set")

val jplLibDir = System.getenv("JPL_LIB_DIR")
	?: error("Environment variable JPL_LIB_DIR is not set")

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
	implementation(files(jplJarPath))

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
	systemProperty("java.library.path", jplLibDir)
}

val copyJplNativeLib by tasks.registering(Copy::class) {
	from(jplLibDir)
	include("libjpl.dylib")
	into(layout.buildDirectory.dir("jpl-libs"))
}

runtime {
	options.set(listOf(
		"--strip-debug",
		"--compress=2",
		"--no-header-files",
		"--no-man-pages"
	))

	modules.set(listOf(
		"java.base",
		"java.desktop",
		"java.logging",
		"java.sql",
		"jdk.unsupported"
	))

	launcher {
		jvmArgs = listOf(
			"-Djava.library.path={{APPDIR}}/jpl-lib"
		)
	}

	jpackage {
		imageName = "PrologPcAdvisor"
		installerName = "PrologPcAdvisor"
		installerType = "app-image"
		appVersion = project.version.toString()
		description = "ScalaFX + SWI-Prolog + JPL demo application"
	}
}

tasks.named("jpackageImage") {
	dependsOn(copyJplNativeLib)

	doLast {
		val imageDir = layout.buildDirectory
			.dir("jpackage/PrologPcAdvisor.app/Contents/app/jpl-lib")
			.get()
			.asFile

		imageDir.mkdirs()

		copy {
			from(layout.buildDirectory.dir("jpl-libs"))
			into(imageDir)
		}
	}
}
