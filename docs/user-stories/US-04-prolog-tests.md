# US-04 Prolog tests

## Objetivo
Introducir una base mínima de conocimiento en Prolog y establecer tests unitarios con `plunit` siguiendo una filosofía TDD.

## Alcance
- crear un módulo Prolog de catálogo
- definir un conjunto mínimo de CPUs
- exponer una consulta para obtener las CPUs disponibles
- crear tests unitarios con `plunit`

## Criterio de hecho
- existe un módulo `catalog.pl`
- existe al menos una consulta para listar CPUs
- los tests de `plunit` pasan correctamente
- se puede ejecutar la batería de tests desde línea de comandos

## Archivos afectados
- `scala-workspace/src/main/resources/prolog/catalog.pl`
- `scala-workspace/src/main/resources/prolog/tests/test_catalog.pl`

## Notas
En esta historia todavía no se integra Prolog con Scala.

Queda para historias posteriores:
- placas base
- RAM
- reglas de compatibilidad
- integración mediante JPL
- renderizado dinámico de UI a partir de Prolog

