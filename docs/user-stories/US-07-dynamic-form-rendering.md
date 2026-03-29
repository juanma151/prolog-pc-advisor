# US-07 Dynamic form rendering

## Objetivo
Construir dinámicamente un formulario en ScalaFX a partir de una definición de campos en Scala, preparando el terreno para que en historias posteriores esa definición provenga de Prolog.

## Alcance
- crear un modelo `UiField`
- crear tests unitarios para el modelo
- crear un renderer sencillo de formulario dinámico
- actualizar la aplicación principal para mostrar el formulario generado

## Criterio de hecho
- existe un modelo `UiField`
- existen tests unitarios que pasan
- la app renderiza dinámicamente un formulario básico
- la definición del formulario no está cableada control a control en la vista principal

## Archivos afectados
- `scala-workspace/src/main/scala/app/ui/UiField.scala`
- `scala-workspace/src/test/scala/app/ui/UiFieldSpec.scala`
- `scala-workspace/src/main/scala/app/ui/FormRenderer.scala`
- `scala-workspace/src/main/scala/app/Main.scala`

## Notas
En esta historia todavía no se usa JPL ni se lee la definición real desde Prolog.

Queda para historias posteriores:
- mapear `field/4` desde Prolog a `UiField`
- usar JPL para leer el esquema real
- reenviar eventos de la JVM a Prolog
