# MiPrimeraApp

Implementación del taller de Android Studio con Kotlin en una app funcional de **lista de tareas** usando **View Binding** y **RecyclerView**.

## Qué incluye

- Crear y eliminar tareas
- Marcar tareas como completadas
- Contador de tareas pendientes
- Layouts XML del taller
- Documentación de las 7 partes del taller en `docs/taller/`

## Paquete usado

El proyecto real usa el paquete:

- `com.example.miprimeraapp`

La guía adjunta usa `com.ejemplo.miprimeraapp`; aquí se mantuvo el paquete actual del proyecto para evitar romper la configuración existente.

## Archivos principales

- `app/src/main/java/com/example/miprimeraapp/MainActivity.kt`
- `app/src/main/java/com/example/miprimeraapp/Tarea.kt`
- `app/src/main/java/com/example/miprimeraapp/TareaAdapter.kt`
- `app/src/main/res/layout/activity_main.xml`
- `app/src/main/res/layout/item_tarea.xml`

## Cómo ejecutar

```powershell
.\gradlew.bat assembleDebug
```

Si tienes un emulador o dispositivo conectado:

```powershell
.\gradlew.bat installDebug
```

## Documentación del taller

- `docs/taller/README.md`
- `docs/taller/parte-1-configuracion.md`
- `docs/taller/parte-2-kotlin.md`
- `docs/taller/parte-3-ejercicios.md`
- `docs/taller/parte-4-estructura-android.md`
- `docs/taller/parte-5-lista-tareas.md`
- `docs/taller/parte-6-cierre.md`
- `docs/taller/parte-7-extensiones.md`

