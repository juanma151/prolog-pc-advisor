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

## Validación realizada

Se ha comprobado que `nix develop` expone correctamente las herramientas necesarias.

### Versiones verificadas
- Java: `21.0.8`
- Gradle: `8.14.4`
- Scala: `3.3.6`
- SWI-Prolog: `9.2.9`

### Comandos ejecutados

```bash
java --version
gradle -v
scala -version
swipl --version
```