package com.example.lascuentasdesofy.ui.Multiplicacion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.lascuentasdesofy.core.IngresarFactoresDialogFragment
import com.example.lascuentasdesofy.databinding.FragmentMultiplicacionBinding

class MultiplicacionFragment : Fragment() {

    private var _binding: FragmentMultiplicacionBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMultiplicacionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setFragmentResultListener("requestKey") { requestKey, bundle ->
            val primerFactor = bundle.getString("primerFactor")
            val segundoFactor = bundle.getString("segundoFactor")

            // Verificación de nulos
            if (primerFactor != null) {
                binding.PrimerFactor.text = primerFactor
            }
            if (segundoFactor != null) {
                binding.SegundoFactor.text = segundoFactor
            }
        }
        mostrarDialog()

        //Levanto los datos del dialog
        //cargarFactoresDesdeElDialog()

        /*binding.buttonMultiplicacion.setOnClickListener {

            var dialogTabla = TablaPtagoricaDialogFragment()

            dialogTabla.show(childFragmentManager, "TablaPitagorica")

        }*/

        return root
    }

    private fun cargarFactoresDesdeElDialog() {
        binding.PrimerFactor.text = arguments?.getString("Primer Factor")
        binding.SegundoFactor.text = arguments?.getString("Segundo Factor")
    }

    private fun mostrarDialog() {
        var ingresarFactores = IngresarFactoresDialogFragment()
        ingresarFactores.show(childFragmentManager, "Ingresar Factores")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}