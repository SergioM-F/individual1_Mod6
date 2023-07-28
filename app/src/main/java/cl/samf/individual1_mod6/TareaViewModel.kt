package cl.samf.individual1_mod6

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TareaViewModel(aplicacion : Application): AndroidViewModel(aplicacion) {
    private val repositorio: Repositorio
    init {
        repositorio = Repositorio(TareaBaseDatos.getDatabase(aplicacion).getTaskDao())

    }

    fun obtenerTareas(): LiveData<List<Tarea>> {
        return repositorio.obtenerTarea()

    }

    fun insertarTareas(tarea: Tarea)=viewModelScope.launch{
        repositorio.insertTarea(tarea)
    }

}