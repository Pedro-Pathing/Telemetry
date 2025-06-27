import com.android.builder.symbols.exportToCompiledJava
import org.jetbrains.dokka.plugability.configuration

plugins {
	id("org.jetbrains.kotlin.android") version "1.9.20"
	id("org.jetbrains.kotlin.plugin.serialization") version "1.9.20"
	id("com.android.library") version "8.1.2"

	`maven-publish`

	id("org.jetbrains.dokka") version "1.9.20"
}

val defaultMinSdkVersion by extra(29)
val defaultMinSdkVersion1 by extra(24)

repositories {
	mavenCentral()
	google()
}

android {
	namespace = "com.pedropathing.pedropathing"
	compileSdk = 32

	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
	publishing {
		singleVariant("release") {
			withSourcesJar()
			withJavadocJar()
		}

	}
	sourceSets {
		getByName("main") {
			java.srcDirs("src/main/kotlin")

		}
	}
	defaultConfig {
		minSdk = 24
	}
}

android.libraryVariants.configureEach {
	val newName = "generate${name.replaceFirstChar { it.uppercase() }}Javadoc"
	val newJavadocTask = tasks.register<Javadoc>(newName) {
		group = "Documentation"
		description = "Generates Javadoc for $name"
		source = javaCompileProvider.get().source
		val androidJar =
			"${android.sdkDirectory}/platforms/${android.compileSdkVersion}/android.jar"
		classpath = files(getCompileClasspath(null)) + files(androidJar)
		with(options as StandardJavadocDocletOptions) {
			memberLevel = JavadocMemberLevel.PROTECTED
			links("https://developer.android.com/reference")
			encoding = "UTF-8"
		}
	}

	if (name == "release") {
		tasks.named<Jar>("javadocJar") {
			dependsOn(newJavadocTask)
			from(newJavadocTask.map { it.destinationDir!! })
		}
	}
}

tasks.register<Jar>("javadocJar") {
	archiveClassifier = "javadoc"
}

tasks.register<Jar>("sourcesJar") {
	from(android.sourceSets["main"].java.srcDirs)
	archiveClassifier = "sources"
}

dependencies {
	//noinspection Aligned16KB
	compileOnly("org.firstinspires.ftc:RobotCore:10.3.0")
	compileOnly("org.firstinspires.ftc:Hardware:10.3.0")
	compileOnly("org.firstinspires.ftc:FtcCommon:10.3.0")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.20")
}


publishing {
	publications {
		register<MavenPublication>("release") {
			groupId = "com.pedropathing"
			artifactId = "telemetry"
			version = "0.0.5"

			afterEvaluate {
				from(components["release"])
			}
		}
	}

	repositories {
		maven {
			name = "publishing"
			url = uri("./maven.pedropathing.com")
		}
	}
}
