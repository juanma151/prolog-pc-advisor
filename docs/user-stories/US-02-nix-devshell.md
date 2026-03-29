# US-02 Nix dev shell

## Objetivo
Disponer de un entorno reproducible con Nix para desarrollar la aplicación.

## Alcance
- añadir `flake.nix`
- usar `flake-parts`
- separar la lógica en `nix/`
- exponer un `devShell` con JDK, Gradle, Scala y SWI-Prolog

## Criterio de hecho
- `nix develop` funciona
- dentro del shell están disponibles `java`, `gradle` y `swipl`

## Archivos afectados
- `flake.nix`
- `nix/flake-module.nix`
- `nix/devshell.nix`

## Notas
Pendiente más adelante:
- exponer `apps`
- integrar JPL
- automatizar ejecución de Gradle en `scala-workspace`

