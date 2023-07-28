package cl.samf.individual1_mod6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import cl.samf.individual1_mod6.databinding.FragmentAgregarBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class AgregarFragment : Fragment() {

    lateinit var binding: FragmentAgregarBinding
    private val tareaViewModel : TareaViewModel by activityViewModels()

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

        val tarea = Tarea(texto,"")
        tareaViewModel.insertarTareas(tarea)

    }

    private fun cargarTareas() {

        tareaViewModel.obtenerTareas().observe(viewLifecycleOwner){
            val tareasTexto = it.joinToString("\n") { it.nombre + it.fecha } //se puede concatenar con + para agregar datos que uno quiera sumar o solo el it para agregar todo
            binding.textViewMostrar.text = tareasTexto

        }


    }


}