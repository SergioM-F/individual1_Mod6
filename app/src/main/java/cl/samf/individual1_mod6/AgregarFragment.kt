package cl.samf.individual1_mod6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cl.samf.individual1_mod6.databinding.FragmentAgregarBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class AgregarFragment : Fragment() {

    lateinit var binding: FragmentAgregarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAgregarBinding.inflate(layoutInflater,container,false)

        initListener()
        cargarTareas()
        return binding.root
    }

    private fun initListener() {
    binding.buttonAgregar.setOnClickListener{
        val texto = binding.editTextIngresoTarea.text.toString()
        guardarTarea(texto)
    }
    }

    private fun guardarTarea(texto:String) {
        val dao= TareaBaseDatos.getDatabase(requireContext()).getTaskDao()
        val tarea = Tarea(texto, "10-05-2023")
        GlobalScope.launch { dao.insertarTarea(tarea) }

    }

    private fun cargarTareas() {
        val dao= TareaBaseDatos.getDatabase(requireContext()).getTaskDao()
        GlobalScope.launch {
            val tareas = dao.getTareas()
            val tareasTexto = tareas.joinToString("\n") { it.nombre + it.fecha }
            binding.textViewMostrar.text = tareasTexto
        }

    }


}