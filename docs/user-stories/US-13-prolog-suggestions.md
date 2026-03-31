# US-13 Prolog suggestions

## Objetivo
Añadir sugerencias de configuración desde Prolog para ayudar al usuario cuando la selección actual no sea válida.

## Alcance
- crear reglas Prolog de sugerencia
- añadir tests Prolog para esas reglas
- ampliar el modelo de resultado en Scala
- ampliar el gateway para recuperar sugerencias
- mostrar la sugerencia en la interfaz

## Criterio de hecho
- una configuración inválida puede generar una sugerencia
- la sugerencia proviene de Prolog
- la app la muestra al usuario
- los tests Prolog pasan
- los tests Scala pasan

## Archivos afectados
- `scala-workspace/src/main/resources/prolog/rules.pl`
- `scala-workspace/src/main/resources/prolog/tests/test_rules.pl`
- `scala-workspace/src/main/scala/app/model/ValidationResult.scala`
- `scala-workspace/src/test/scala/app/model/ValidationResultSpec.scala`
- `scala-workspace/src/main/scala/app/prolog/JplPrologGateway.scala`
- `scala-workspace/src/main/scala/app/Main.scala`

## Decisiones de diseño
- se introduce el modelo `BuildSelection` para representar explícitamente la selección actual del usuario
- el gateway Prolog deja de trabajar con tres `String` sueltas y pasa a trabajar con un objeto de dominio
- esto reduce duplicación y prepara mejor futuras ampliaciones del configurador

## Notas
En esta historia se devuelve una sugerencia simple, no una lista de alternativas ordenadas.
