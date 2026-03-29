# US-10 Validate build

## Objetivo
Permitir que el usuario seleccione CPU, placa base y RAM, y que Prolog determine si la configuración es válida.

## Alcance
- añadir reglas de compatibilidad en Prolog
- añadir tests Prolog para esas reglas
- añadir un resultado de validación en Scala
- añadir un botón de validación en la UI
- mostrar el resultado al usuario

## Criterio de hecho
- la app permite seleccionar componentes
- la validación se ejecuta al pulsar un botón
- Prolog decide si la combinación es válida o no
- los tests Prolog pasan
- la UI muestra el resultado

## Archivos afectados
- `scala-workspace/src/main/resources/prolog/catalog.pl`
- `scala-workspace/src/main/resources/prolog/rules.pl`
- `scala-workspace/src/main/resources/prolog/tests/test_rules.pl`
- `scala-workspace/src/main/scala/app/model/ValidationResult.scala`
- `scala-workspace/src/test/scala/app/model/ValidationResultSpec.scala`
- `scala-workspace/src/main/scala/app/prolog/PrologGateway.scala`
- `scala-workspace/src/main/scala/app/prolog/JplPrologGateway.scala`
- `scala-workspace/src/main/scala/app/ui/FormRenderer.scala`
- `scala-workspace/src/main/scala/app/Main.scala`

## Notas
En esta historia solo se valida compatibilidad básica:
- socket CPU ↔ placa base
- tipo de RAM ↔ placa base

Todavía no se valida:
- potencia de fuente
- equilibrio CPU/GPU
- recomendaciones avanzadas
