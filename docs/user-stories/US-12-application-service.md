# US-12 Application service

## Objetivo
Extraer la lógica de aplicación a una capa de servicio testeable en Scala, separando mejor la UI de la orquestación del dominio.

## Alcance
- crear un servicio de aplicación
- mover al servicio la carga de campos
- mover al servicio la validación de selección
- añadir tests unitarios del servicio usando un doble de `PrologGateway`

## Criterio de hecho
- existe un servicio de aplicación testeado
- `Main` queda más simple
- los tests del servicio no dependen de JavaFX ni de JPL real
- la aplicación sigue funcionando igual

## Archivos afectados
- `scala-workspace/src/main/scala/app/service/BuildAdvisorService.scala`
- `scala-workspace/src/test/scala/app/service/BuildAdvisorServiceSpec.scala`
- `scala-workspace/src/main/scala/app/Main.scala`

## Notas
En esta historia no se añade funcionalidad nueva visible para el usuario.
El objetivo es mejorar la arquitectura y la testabilidad.
