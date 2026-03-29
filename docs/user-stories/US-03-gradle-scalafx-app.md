# US-03 Gradle ScalaFX app

## Objetivo
Crear una aplicación mínima de escritorio usando Gradle con Kotlin DSL, Scala 3 y ScalaFX.

## Alcance
- añadir `settings.gradle.kts`
- añadir `build.gradle.kts`
- añadir `gradle.properties`
- inicializar el wrapper de Gradle
- crear una aplicación mínima con ventana
- dejar preparado el proyecto para añadir tests e integración con Prolog en historias posteriores

## Criterio de hecho
- `./gradlew run` funciona dentro de `scala-workspace`
- se abre una ventana JavaFX básica
- el proyecto queda preparado para añadir tests en Scala
- el proyecto mantiene separada la parte Nix de la parte Gradle

## Archivos afectados
- `scala-workspace/settings.gradle.kts`
- `scala-workspace/build.gradle.kts`
- `scala-workspace/gradle.properties`
- `scala-workspace/src/main/scala/app/Main.scala`

## Notas
Pendiente más adelante:
- tests en Scala
- integración JPL
- carga dinámica de datos desde Prolog
- renderizado dinámico de formulario

