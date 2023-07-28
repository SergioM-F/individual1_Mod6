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
    lateinit var repositorio: Repositorio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAgregarBinding.inflate(layoutInflater,container,false)

        initRepositorio()
        initListener()
        cargarTareas()
        return binding.root
    }

    private fun initRepositorio() {
        repositorio = Repositorio(TareaBaseDatos.getDatabase(requireContext()).getTaskDao())
    }

    private fun initListener() {
    binding.buttonAgregar.setOnClickListener{
        val texto = binding.editTextIngresoTarea.text.toString()
        guardarTarea(texto)
    }
    }

    private fun guardarTarea(texto:String) {

        val tarea = Tarea(texto, "10-05-2023")
        GlobalScope.launch { repositorio.insertTarea(tarea) }

    }

    private fun cargarTareas() {

        repositorio.obtenerTarea().observe(requireActivity()){
            val tareasTexto = it.joinToString("\n") { it.nombre + it.fecha } //se puede concatenar con + para agregar datos que uno quiera sumar o solo el it para agregar todo
            binding.textViewMostrar.text = tareasTexto

        }


    }


}