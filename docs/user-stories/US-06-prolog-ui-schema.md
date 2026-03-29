# US-06 Prolog UI schema

## Objetivo
Definir en Prolog un esquema mínimo del formulario principal para que la JVM pueda, en historias posteriores, construir la interfaz dinámicamente a partir de esa información.

## Alcance
- crear un módulo `ui_schema.pl`
- definir los campos visibles del formulario
- definir tipo de control, etiqueta y orden
- crear tests unitarios con `plunit`

## Criterio de hecho
- existe un módulo `ui_schema.pl`
- el módulo expone una consulta para obtener la definición de campos
- existen tests de `plunit` para validar el esquema
- los tests pasan correctamente

## Archivos afectados
- `scala-workspace/src/main/resources/prolog/ui_schema.pl`
- `scala-workspace/src/main/resources/prolog/tests/test_ui_schema.pl`

## Notas
En esta historia todavía no se renderiza la UI desde Scala.

Queda para historias posteriores:
- leer el esquema desde Scala
- renderizar controles dinámicamente
- conectar eventos JVM -> Prolog
- devolver acciones de UI desde Prolog