package com.example.lascuentasdesofy.ui.Suma

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lascuentasdesofy.R
import com.example.lascuentasdesofy.core.hideKeyboard
import com.example.lascuentasdesofy.databinding.FragmentSumaBinding

class SumaFragment : Fragment() {

    private var _binding: FragmentSumaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSumaBinding.inflate(inflater, container, false)
        val root: View = binding.root


        //Almaceno los datos ingresados en una variable
        var primerValor = binding.setPrimerValor
        var segundoValor = binding.setSegundoValor
        val MAX_CIFRAS = 5

        binding.buttonDescoponerSuma.setOnClickListener{
            //Ocultamos el teclado
            it.hideKeyboard()
            //Completo con cero los valores que no fueron ingresados
            var primerValorCompleto = primerValor.text.padStart(MAX_CIFRAS, '0')
            var segundoValorCompleto = segundoValor.text.padStart(MAX_CIFRAS, '0')
            //TODO descoponer los valores ingresados y ubicar en los TextView correspondientes a
            // cada unidad para luego operar en cada unidad
            descomponerPrimerValor(primerValorCompleto)
            descomponerSegundoValor(segundoValorCompleto)
        }

        binding.buttonResultadoSuma.setOnClickListener{
            val DMValor1 = binding.decenasDeMilValor1.text.toString().toInt()
            val DMValor2 = binding.decenasDeMilValor2.text.toString().toInt()
            val UMValor1 = binding.unidadesDeMilValor1.text.toString().toInt()
            val UMValor2 = binding.unidadesDeMilValor2.text.toString().toInt()
            val CValor1 = binding.centenasValor1.text.toString().toInt()
            val CValor2 = binding.centenasValor2.text.toString().toInt()
            val DValor1 = binding.decenasValor1.text.toString().toInt()
            val DValor2 = binding.decenasValor2.text.toString().toInt()
            val UValor1 = binding.unidadesValor1.text.toString().toInt()
            val UValor2 = binding.unidadesValor2.text.toString().toInt()

            val resultadoDM = DMValor1.plus(DMValor2)
            val resultadoUM = UMValor1.plus(UMValor2)
            val resultadoC = CValor1.plus(CValor2)
            val resultadoD = DValor1.plus(DValor2)
            val resultadoU = UValor1.plus(UValor2)

            binding.resultadoDecenasDeMil.text = resultadoDM.toString()
            binding.resultadoUnidadDeMil.text = resultadoUM.toString()
            binding.resultadoCentena.text = resultadoC.toString()
            binding.resultadoDecena.text = resultadoD.toString()
            binding.resultadoUnidad.text = resultadoU.toString()
        }

        binding.buttonFinalizarCuenta.setOnClickListener {
            val DecDeMil = binding.resultadoDecenasDeMil.text.toString().toInt()
            val UniDeMil = binding.resultadoUnidadDeMil.text.toString().toInt()
            val Centenas = binding.resultadoCentena.text.toString().toInt()
            val Decenas = binding.resultadoDecena.text.toString().toInt()
            val Unidad = binding.resultadoUnidad.text.toString().toInt()

            val resultadoTotal = DecDeMil.plus(UniDeMil.plus(Centenas.plus(Decenas.plus(Unidad))))

            binding.TotalFinal.text = resultadoTotal.toString()

            binding.TotalFinal.text
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        completarConCeros()

        binding.iconoSumaDM.setImageResource(R.mipmap.ic_menu_suma)
        binding.iconoIgualDM.setImageResource(R.mipmap.ic_igual)
        binding.iconoSumaUM.setImageResource(R.mipmap.ic_menu_suma)
        binding.iconoIgualUM.setImageResource(R.mipmap.ic_igual)
        binding.iconoSumaC.setImageResource(R.mipmap.ic_menu_suma)
        binding.iconoIgualC.setImageResource(R.mipmap.ic_igual)
        binding.iconoSumaD.setImageResource(R.mipmap.ic_menu_suma)
        binding.iconoIgualD.setImageResource(R.mipmap.ic_igual)
        binding.iconoSumaU.setImageResource(R.mipmap.ic_menu_suma)
        binding.iconoIgualU.setImageResource(R.mipmap.ic_igual)


    }

    private  fun descomponerPrimerValor(primerValor: CharSequence){
        var iteracion = 0
        for (valor in primerValor){
            when(iteracion){
                0-> binding.decenasDeMilValor1.text = valor.toString()+"0000"
                1-> binding.unidadesDeMilValor1.text = valor.toString()+"000"
                2-> binding.centenasValor1.text = valor.toString()+"00"
                3-> binding.decenasValor1.text = valor.toString()+'0'
                4-> binding.unidadesValor1.text = valor.toString()
                else -> break
            }
            iteracion++
        }
    }
    private fun descomponerSegundoValor(segundoValor: CharSequence) {
        var iteracion = 0
        for (valor in segundoValor){
            when(iteracion){
                0-> binding.decenasDeMilValor2.text = valor.toString()+"0000"
                1-> binding.unidadesDeMilValor2.text = valor.toString()+"000"
                2-> binding.centenasValor2.text = valor.toString()+"00"
                3-> binding.decenasValor2.text = valor.toString()+'0'
                4-> binding.unidadesValor2.text = valor.toString()
                else -> break
            }
            iteracion++
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun completarConCeros() {
        val MAX_DM = "00000"
        val MAX_UM = "0000"
        val MAX_C = "000"
        val MAX_D = "00"
        val MAX_U = "0"
        binding.decenasDeMilValor1.text = MAX_DM
        binding.decenasDeMilValor2.text = MAX_DM
        binding.unidadesDeMilValor1.text = MAX_UM
        binding.unidadesDeMilValor2.text = MAX_UM
        binding.centenasValor1.text = MAX_C
        binding.centenasValor2.text = MAX_C
        binding.decenasValor1.text = MAX_D
        binding.decenasValor2.text = MAX_D
        binding.unidadesValor1.text = MAX_U
        binding.unidadesValor2.text = MAX_U
    }
}


