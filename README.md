# MiPrimeraApp

Proyecto Android en Kotlin basado en el taller de clase. La app implementa una lista de tareas con `RecyclerView` y `View Binding`, permite marcar tareas completadas, elimina tareas y muestra el contador de pendientes.

## Funcionalidades

- Agregar tareas
- Marcar/desmarcar tareas como completadas
- Eliminar tareas
- Contador de tareas pendientes en tiempo real
- Persistencia local de la lista al cerrar y abrir la app

## Tecnologías

- Kotlin
- Android SDK (minSdk 24)
- `RecyclerView`
- `View Binding`
- `SharedPreferences` + `Gson` para persistencia local

## Paquete del proyecto

- `com.example.miprimeraapp`

## Archivos principales

- `app/src/main/java/com/example/miprimeraapp/MainActivity.kt`
- `app/src/main/java/com/example/miprimeraapp/Tarea.kt`
- `app/src/main/java/com/example/miprimeraapp/TareaAdapter.kt`
- `app/src/main/res/layout/activity_main.xml`
- `app/src/main/res/layout/item_tarea.xml`

## Ejecutar el proyecto

```powershell
.\gradlew.bat assembleDebug
```

Con dispositivo o emulador conectado:

```powershell
.\gradlew.bat installDebug
```

## Respuestas del cierre del taller

1. **¿Cuál es la diferencia entre `val` y `var`?**
   - `val` declara una referencia inmutable (no se puede reasignar).
   - `var` declara una referencia mutable (sí se puede reasignar).

2. **¿Para qué sirve el operador `?:` (Elvis)?**
   - Sirve para dar un valor por defecto cuando una expresión nullable es `null`.
   - Ejemplo: `val texto = nombre ?: "Sin nombre"`.

3. **¿Qué genera automáticamente una `data class` que una clase normal no?**
   - Genera automáticamente `toString()`, `equals()`, `hashCode()`, `copy()` y `componentN()`.

4. **¿Qué hace el Adapter en un RecyclerView?**
   - Conecta los datos con las vistas de cada item.
   - Crea ViewHolders y enlaza cada elemento de la lista con su fila visual.

5. **¿Por qué usar View Binding en lugar de `findViewById`?**
   - Porque es type-safe, reduce errores por IDs incorrectos y hace el código más limpio.
