package cl.samf.individual1_mod6

import androidx.lifecycle.LiveData

class Repositorio(private val tareaDao: TareaDao) {

    suspend fun insertTarea(tarea: Tarea){
        tareaDao.insertarTarea(tarea)
    }

    fun obtenerTarea(): LiveData<List<Tarea>> {
        return tareaDao.obtenerTareas()
    }

}