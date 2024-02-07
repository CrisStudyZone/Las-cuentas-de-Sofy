package com.example.lascuentasdesofy.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.example.lascuentasdesofy.R
import com.example.lascuentasdesofy.databinding.DialogFragmentIngresarFactoresBinding
import com.example.lascuentasdesofy.ui.Multiplicacion.MultiplicacionFragment

class IngresarFactoresDialogFragment : DialogFragment() {

    private var _binding: DialogFragmentIngresarFactoresBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DialogFragmentIngresarFactoresBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.multiplicacion.setImageResource(R.mipmap.ic_menu_multiplicacion)
        binding.TituloDePagina.text = "Ingresa los factores a multiplicar"

        binding.Aceptar.setOnClickListener {

            val primerFactor = binding.setPrimerFactor.text
            val segundoFactor = binding.setSegundoFactor.text

            // Uso la extensión Kotlin en el fragment-ktx
            setFragmentResult("keyPrimerFactor", bundleOf("primerFactor" to primerFactor))
            setFragmentResult("keySegundoFactor", bundleOf("segundoFactor" to segundoFactor))
            dialog?.dismiss()
        }
        /*binding.Aceptar.setOnClickListener {
            var primerFactor = binding.setPrimerFactor.text.toString()
            var segundoFactor = binding.setSegundoFactor.text.toString()

            pasarFactoresAlFragment(primerFactor,segundoFactor)

            dialog?.dismiss()
        }*/
        return root
    }

    private fun pasarFactoresAlFragment(primerFactor: String, segundoFactor: String) {

        val fragmentMultiplicacion = MultiplicacionFragment()
        val bundle = Bundle()
        bundle.putString("Primer Factor", primerFactor)
        bundle.putString("Segundo Factor", segundoFactor)
        fragmentMultiplicacion.arguments = bundle

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}