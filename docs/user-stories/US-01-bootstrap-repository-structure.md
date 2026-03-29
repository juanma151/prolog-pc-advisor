# US-01 Bootstrap repository structure

## Objetivo
Crear la estructura inicial del repositorio para arrancar el proyecto de forma ordenada, separando desde el principio la parte de Nix de la parte del workspace Scala/Gradle.

## Alcance
- crear el repositorio Git local
- preparar la estructura inicial de directorios
- añadir `.gitignore` en la raíz
- añadir `.gitignore` en `scala-workspace/`
- añadir un `README.md` inicial
- dejar preparado el terreno para la US-02

## Criterio de hecho
- el repositorio existe y tiene una estructura base coherente
- existe un `.gitignore` en la raíz
- existe un `.gitignore` dentro de `scala-workspace/`
- existe un `README.md` inicial
- la estructura permite empezar a trabajar en Nix y en Scala/Gradle en historias separadas

## Estructura creada

```text
prolog-pc-advisor/
├── .gitignore
├── README.md
├── nix/
└── scala-workspace/
    ├── .gitignore
    └── src/
        ├── main/
        │   ├── scala/
        │   │   └── app/
        │   └── resources/
        │       └── prolog/
        └── test/
            └── scala/
```

## Archivos afectados
- `.gitignore`
- `README.md`
- `scala-workspace/.gitignore`

## Decisiones tomadas
- el repositorio tendrá una **raíz centrada en Nix**
- el código de la aplicación vivirá dentro de `scala-workspace/`
- habrá un `.gitignore` global en la raíz para cubrir:
  - Nix
  - IDEs/editores
  - backups y temporales
  - archivos del sistema operativo
- habrá un `.gitignore` específico dentro de `scala-workspace/` para cubrir:
  - Gradle
  - Scala
  - artefactos del workspace JVM

## Notas
Esta historia no introduce todavía:
- `flake.nix`
- `flake-parts`
- configuración Gradle
- ScalaFX
- JPL
- tests

Todo eso queda para historias posteriores.

## Siguientes pasos
- US-02: preparar el entorno reproducible con Nix usando `flake-parts`
- US-03: crear la aplicación mínima con Gradle, Scala 3 y ScalaFX
- US-04: empezar con tests en Prolog
- US-05: empezar con tests en Scala
