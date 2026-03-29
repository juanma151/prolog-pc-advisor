# US-09 Load options from Prolog

## Objetivo
Cargar desde Prolog las opciones disponibles para cada campo del formulario y mostrarlas en los controles renderizados dinámicamente.

## Alcance
- ampliar el catálogo de componentes en Prolog
- añadir consultas para obtener opciones por campo
- ampliar el gateway Scala para leer opciones desde Prolog
- renderizar combos con opciones reales

## Criterio de hecho
- los combos muestran opciones reales
- las opciones provienen de Prolog
- la aplicación arranca correctamente
- todavía no se implementa validación ni gestión de eventos

## Archivos afectados
- `scala-workspace/src/main/resources/prolog/catalog.pl`
- `scala-workspace/src/main/resources/prolog/tests/test_catalog.pl`
- `scala-workspace/src/main/scala/app/ui/UiField.scala`
- `scala-workspace/src/main/scala/app/ui/FormRenderer.scala`
- `scala-workspace/src/main/scala/app/prolog/PrologGateway.scala`
- `scala-workspace/src/main/scala/app/prolog/JplPrologGateway.scala`

## Notas
En esta historia todavía no se valida la compatibilidad entre componentes.
Eso quedará para la siguiente historia funcional.
