# Parte 6 — App Lista de Tareas

## Objetivo
Construir una app funcional que permita agregar, completar y eliminar tareas.

## Componentes implementados
- `Tarea.kt`: modelo de datos
- `item_tarea.xml`: layout de cada fila
- `TareaAdapter.kt`: conexión entre la lista de objetos y el RecyclerView
- `activity_main.xml`: formulario, contador y lista
- `MainActivity.kt`: lógica principal

## Funcionalidad
- Agregar tareas no vacías
- Marcar tareas como completadas
- Tachar el texto al completar
- Eliminar tareas de la lista
- Actualizar contador de tareas pendientes

## Punto importante
Se activó **View Binding** en `app/build.gradle.kts` para acceder a las vistas sin `findViewById`.

