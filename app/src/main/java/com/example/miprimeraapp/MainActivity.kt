package com.example.miprimeraapp

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.miprimeraapp.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val listaTareas = mutableListOf<Tarea>()
    private lateinit var adapter: TareaAdapter
    private var contadorId = 0
    private lateinit var prefs: SharedPreferences
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        restaurarEstado(savedInstanceState)
        configurarRecyclerView()
        configurarBotones()
    }

    private fun configurarRecyclerView() {
        adapter = TareaAdapter(listaTareas, { posicion -> eliminarTarea(posicion) }, { actualizarContador() })
        binding.rvTareas.layoutManager = LinearLayoutManager(this)
        binding.rvTareas.adapter = adapter
        actualizarContador()
    }

    private fun configurarBotones() {
        binding.btnAgregar.setOnClickListener {
            val texto = binding.etNuevaTarea.text.toString().trim()
            if (texto.isNotEmpty()) {
                agregarTarea(texto)
                binding.etNuevaTarea.text.clear()
            } else {
                Toast.makeText(this, getString(R.string.write_task_first), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun agregarTarea(titulo: String) {
        contadorId++
        val nuevaTarea = Tarea(contadorId, titulo)
        listaTareas.add(nuevaTarea)
        adapter.notifyItemInserted(listaTareas.lastIndex)
        actualizarContador()
        guardarTareas()
    }

    private fun eliminarTarea(posicion: Int) {
        if (posicion !in listaTareas.indices) return

        listaTareas.removeAt(posicion)
        adapter.notifyItemRemoved(posicion)
        if (posicion < listaTareas.size) {
            adapter.notifyItemRangeChanged(posicion, listaTareas.size - posicion)
        }
        actualizarContador()
        guardarTareas()
    }

    private fun actualizarContador() {
        val pendientes = listaTareas.count { !it.completada }
        binding.tvContador.text = getString(R.string.pending_tasks_format, pendientes)
    }

    private fun guardarTareas() {
        val json = gson.toJson(listaTareas)
        prefs.edit().apply {
            putString(KEY_TAREAS, json)
            putInt(KEY_CONTADOR_ID, contadorId)
            apply()
        }
    }

    private fun cargarTareas() {
        val json = prefs.getString(KEY_TAREAS, null)
        if (json != null) {
            try {
                val type = object : TypeToken<List<Tarea>>() {}.type
                val tareas: List<Tarea> = gson.fromJson(json, type)
                listaTareas.clear()
                listaTareas.addAll(tareas)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        contadorId = prefs.getInt(KEY_CONTADOR_ID, listaTareas.maxOfOrNull { it.id } ?: 0)
    }

    private fun restaurarEstado(savedInstanceState: Bundle?) {
        val ids = savedInstanceState?.getIntegerArrayList(KEY_TAREA_IDS)
        val titulos = savedInstanceState?.getStringArrayList(KEY_TAREA_TITULOS)
        val completadas = savedInstanceState?.getBooleanArray(KEY_TAREA_COMPLETADAS)

        if (ids != null && titulos != null && completadas != null) {
            val cantidad = minOf(ids.size, titulos.size, completadas.size)
            listaTareas.clear()
            repeat(cantidad) { index ->
                listaTareas.add(
                    Tarea(
                        id = ids[index],
                        titulo = titulos[index],
                        completada = completadas[index]
                    )
                )
            }
        } else {
            cargarTareas()
        }

        contadorId = savedInstanceState?.getInt(KEY_CONTADOR_ID)
            ?: contadorId
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntegerArrayList(KEY_TAREA_IDS, ArrayList(listaTareas.map { it.id }))
        outState.putStringArrayList(KEY_TAREA_TITULOS, ArrayList(listaTareas.map { it.titulo }))
        outState.putBooleanArray(KEY_TAREA_COMPLETADAS, BooleanArray(listaTareas.size) { index ->
            listaTareas[index].completada
        })
        outState.putInt(KEY_CONTADOR_ID, contadorId)
    }

    companion object {
        private const val PREFS_NAME = "tareas_prefs"
        private const val KEY_TAREAS = "key_tareas"
        private const val KEY_CONTADOR_ID = "key_contador_id"
        private const val KEY_TAREA_IDS = "key_tarea_ids"
        private const val KEY_TAREA_TITULOS = "key_tarea_titulos"
        private const val KEY_TAREA_COMPLETADAS = "key_tarea_completadas"
    }
}