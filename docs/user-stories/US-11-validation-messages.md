# US-11 Validation messages

## Objetivo
Hacer que la validación devuelva mensajes explicativos, de forma que el usuario entienda por qué una configuración es válida o inválida.

## Alcance
- ampliar reglas de Prolog para devolver mensajes
- distinguir incompatibilidad de socket e incompatibilidad de RAM
- adaptar el gateway Scala
- mostrar el mensaje devuelto por Prolog en la UI

## Criterio de hecho
- Prolog devuelve mensajes explicativos
- la app muestra el mensaje real del resultado
- los tests de reglas Prolog pasan
- el comportamiento sigue gobernado por Prolog

## Archivos afectados
- `scala-workspace/src/main/resources/prolog/rules.pl`
- `scala-workspace/src/main/resources/prolog/tests/test_rules.pl`
- `scala-workspace/src/main/scala/app/prolog/PrologGateway.scala`
- `scala-workspace/src/main/scala/app/prolog/JplPrologGateway.scala`

## Notas
En esta historia todavía no se generan recomendaciones alternativas.
