package cl.samf.individual1_mod6

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface TareaDao {
    @Insert
    suspend fun insertarTarea(tarea: Tarea)
}