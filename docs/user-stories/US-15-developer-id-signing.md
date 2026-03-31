# US-15 Developer ID signing

## Objetivo
Preparar la firma real de la aplicación para macOS usando una identidad válida de Apple Developer, de forma que la app pueda abrirse como una aplicación normal de macOS.

## Alcance
- comprobar identidades de firma disponibles en el sistema
- documentar el requisito de `Developer ID Application`
- preparar el proyecto para integrar firma real en el empaquetado
- validar si la firma puede realizarse desde este equipo

## Criterio de hecho
- se identifica al menos una identidad de firma válida, o se documenta su ausencia
- queda claro qué identidad debe usarse para firmar la app
- se deja preparado el siguiente paso técnico para integrar la firma en el build
- se documenta el estado real del equipo respecto a firma y apertura de la app

## Archivos afectados
- `docs/user-stories/US-15-developer-id-signing.md`
- `docs/user-stories/README.md`

## Estado inicial
Situación detectada antes de empezar esta historia:

@@@bash
security find-identity -v -p codesigning
@@@

Resultado observado inicialmente:

@@@text
0 valid identities found
@@@

Esto indica que, al inicio de la historia, no había identidades de firma utilizables en el llavero del sistema.

## Hipótesis de trabajo
Existe una cuenta de Apple Developer, pero todavía no estaban instaladas en el llavero las identidades necesarias para firmar la aplicación.

La siguiente comprobación se hará tras revisar Xcode y la gestión de certificados de firma.

## Notas
- La aplicación ya se empaqueta correctamente como `.app`.
- El ejecutable interno del bundle funciona.
- El problema pendiente está en la firma/confianza de macOS al abrir la `.app` como aplicación normal.
- Esta historia se centra en preparar la firma real, no en la notarización.
