# US-05 Scala tests

## Objetivo
Preparar el workspace Scala para ejecutar tests unitarios y establecer una base TDD en la parte JVM.

## Alcance
- añadir dependencias de test
- configurar la ejecución de tests en Gradle
- crear una clase Scala simple de dominio
- crear tests unitarios para esa clase

## Criterio de hecho
- `./gradlew test` funciona
- existe al menos una clase de dominio testeada
- los tests pasan correctamente
- todavía no se integra JPL ni JavaFX en los tests

## Archivos afectados
- `scala-workspace/build.gradle.kts`
- `scala-workspace/src/main/scala/app/model/Cpu.scala`
- `scala-workspace/src/test/scala/app/model/CpuSpec.scala`

## Notas
En esta historia no se prueba todavía:
- JavaFX
- ScalaFX
- JPL
- integración con Prolog

La intención es fijar una base de test unitaria pura para la parte Scala.
