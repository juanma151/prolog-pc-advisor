# US-14 macOS packaging

## Objetivo
Empaquetar la aplicación como una app nativa para macOS.

## Alcance
- añadir configuración de `jpackage`
- generar una `app-image` para macOS
- incluir la librería nativa `libjpl.dylib` en el bundle
- documentar el proceso de construcción

## Criterio de hecho
- `./gradlew jpackageImage` funciona
- se genera `PrologPcAdvisor.app`
- la aplicación empaquetada arranca correctamente en macOS
- JPL sigue funcionando dentro de la app empaquetada

## Archivos afectados
- `scala-workspace/build.gradle.kts`
- `docs/user-stories/US-14-macos-packaging.md`

## Notas
En esta historia se genera primero una `app-image`.
La generación de `.dmg` o firma/notarización queda para una fase posterior si hace falta.
