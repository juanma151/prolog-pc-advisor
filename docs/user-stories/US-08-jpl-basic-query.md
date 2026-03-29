# US-08 JPL basic query

## Objetivo
Leer desde Scala el esquema de UI definido en Prolog usando JPL, y transformarlo a `UiField` para reutilizar el renderer dinámico ya existente.

## Alcance
- exponer JPL en el entorno de desarrollo
- añadir `jpl.jar` al proyecto Gradle
- configurar acceso a la librería nativa `libjpl`
- crear un gateway básico hacia Prolog
- consultar `field/4` desde `ui_schema.pl`
- transformar los resultados a `UiField`

## Criterio de hecho
- la aplicación arranca
- Scala puede consultar Prolog mediante JPL
- los campos del formulario ya no están hardcodeados en `Main`
- el formulario se renderiza a partir de resultados obtenidos desde Prolog

## Archivos afectados
- `nix/devshell.nix`
- `scala-workspace/build.gradle.kts`
- `scala-workspace/src/main/scala/app/prolog/PrologGateway.scala`
- `scala-workspace/src/main/scala/app/prolog/JplPrologGateway.scala`
- `scala-workspace/src/main/scala/app/Main.scala`

## Notas
En esta historia todavía no se manejan eventos ni actualización dinámica de la interfaz desde Prolog.
