package com.example.lascuentasdesofy.ui.Inicio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lascuentasdesofy.R
import com.example.lascuentasdesofy.databinding.FragmentInicioBinding


class InicioFragment : Fragment() {

    private var _binding: FragmentInicioBinding? = null
    private lateinit var inicioViewModel: InicioViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        inicioViewModel =
            ViewModelProvider(this).get(InicioViewModel::class.java)

        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val buttonSuma:ImageView = binding.buttonSuma
        inicioViewModel.imagenSuma.observe(viewLifecycleOwner){ suma ->
            buttonSuma.setImageDrawable(suma)
        }
        inicioViewModel.cargaDeImagenSuma("https://poliysusamigos.com/wp-content/uploads/2022/05/la-suma-aprendamos-a-sumar.jpg")
        buttonSuma.setOnClickListener {
            findNavController().navigate(R.id.action_nav_inicio_to_nav_suma)
        }

        val buttonResta = binding.buttonResta
        inicioViewModel.imagenResta.observe(viewLifecycleOwner){ resta ->
            buttonResta.setImageDrawable(resta)
        }
        inicioViewModel.cargaDeImagenResta("https://poliysusamigos.com/wp-content/uploads/2022/05/la-resta.jpg")
        buttonResta.setOnClickListener {
            findNavController().navigate(R.id.action_nav_inicio_to_nav_resta)
        }

        val buttonDivision = binding.buttonDivision
        inicioViewModel.imagenDivision.observe(viewLifecycleOwner){ division ->
            buttonDivision.setImageDrawable(division)
        }
        inicioViewModel.cargaDeImagenDivision("https://poliysusamigos.com/wp-content/uploads/2022/06/division.jpg")
        buttonDivision.setOnClickListener {
            findNavController().navigate(R.id.action_nav_inicio_to_nav_division)
        }

        val buttonMultiplicacion = binding.buttonMultiplicacion
        inicioViewModel.imagenMultiplicacion.observe(viewLifecycleOwner){ multiplicacion ->
            buttonMultiplicacion.setImageDrawable(multiplicacion)
        }
        inicioViewModel.cargaDeImagenMultiplicacion("https://poliysusamigos.com/wp-content/uploads/2022/06/la-multiplicacion-aprendamos-a-multiplicar.jpg")
        buttonMultiplicacion.setOnClickListener {
            findNavController().navigate(R.id.action_nav_inicio_to_nav_multiplicacion)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}