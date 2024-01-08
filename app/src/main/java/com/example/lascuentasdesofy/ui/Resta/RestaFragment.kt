package com.example.lascuentasdesofy.ui.Resta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lascuentasdesofy.R
import com.example.lascuentasdesofy.core.hideKeyboard
import com.example.lascuentasdesofy.databinding.FragmentRestaBinding


class RestaFragment : Fragment() {

    private var _binding: FragmentRestaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRestaBinding.inflate(inflater, container, false)
        val root: View = binding.root


        //Almaceno los datos ingresados en una variable
        var primerValor = binding.setPrimerValor
        var segundoValor = binding.setSegundoValor

        binding.buttonDescoponer.setOnClickListener{
            //Oculto el teclado
            it.hideKeyboard()
            //Ordeno los valores para que el numero no sea negativo
            val valoresOrdenados = ordenarValores(primerValor, segundoValor)
            val primerValorCompleto = valoresOrdenados.first
            val segundoValorCompleto = valoresOrdenados.second
            //TODO descoponer los valores ingresados y ubicar en los TextView correspondientes a
            // cada unidad para luego operar en cada unidad
            descomponerPrimerValor(primerValorCompleto)
            descomponerSegundoValor(segundoValorCompleto)
        }

        binding.buttonResultado.setOnClickListener{

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

            val resultadoDM = DMValor1.minus(DMValor2)
            val resultadoUM = UMValor1.minus(UMValor2)
            val resultadoC = CValor1.minus(CValor2)
            val resultadoD = DValor1.minus(DValor2)
            val resultadoU = UValor1.minus(UValor2)

            binding.resultadoDecenasDeMil.text = resultadoDM.toString()
            binding.resultadoUnidadDeMil.text = resultadoUM.toString()
            binding.resultadoCentena.text = resultadoC.toString()
            binding.resultadoDecena.text = resultadoD.toString()
            binding.resultadoUnidad.text = resultadoU.toString()
        }

        binding.buttonFinalizarCuenta.setOnClickListener {
            val ResDecDeMil = binding.resultadoDecenasDeMil.text.toString()
            val ResUniDeMil = binding.resultadoUnidadDeMil.text.toString()
            val ResCentenas = binding.resultadoCentena.text.toString()
            val ResDecenas = binding.resultadoDecena.text.toString()
            val ResUnidad = binding.resultadoUnidad.text.toString()

            //Verifico que el boton de igual lo haya presionado para tener los iguales parciales
            if (ResDecDeMil.isNotEmpty() && ResUniDeMil.isNotEmpty() && ResCentenas.isNotEmpty() && ResDecenas.isNotEmpty() && ResUnidad.isNotEmpty()){
                val DecDeMil = ResDecDeMil.toInt()
                val UniDeMil = ResUniDeMil.toInt()
                val Centenas = ResCentenas.toInt()
                val Decenas = ResDecenas.toInt()
                val Unidad = ResUnidad.toInt()
                val resultadoTotal = DecDeMil.plus(UniDeMil.plus(Centenas.plus(Decenas.plus(Unidad))))
                binding.TotalFinal.text = resultadoTotal.toString()

                binding.TotalFinal.text
            }else{
                Toast.makeText(context, "Debemos primero igualer los grupos por separado", Toast.LENGTH_SHORT).show()
            }
        }
        return root
    }

    private fun ordenarValores(primerValor: EditText, segundoValor: EditText): Pair<CharSequence, CharSequence> {
        val MAX_CIFRAS = 5

        var valorTexto1 = primerValor.text.toString()
        var valorTexto2 = segundoValor.text.toString()

        val primerValorNumerico = if (valorTexto1.isNotEmpty()) valorTexto1.toInt() else 0
        val segundoValorNumerico = if (valorTexto2.isNotEmpty()) valorTexto2.toInt() else 0

        var primerValorCompleto: String
        var segundoValorCompleto: String

        if (primerValorNumerico >= segundoValorNumerico) {
            primerValorCompleto = valorTexto1.padStart(MAX_CIFRAS, '0')
            segundoValorCompleto = valorTexto2.padStart(MAX_CIFRAS, '0')
        } else {
            segundoValorCompleto = valorTexto1.padStart(MAX_CIFRAS, '0')
            primerValorCompleto = valorTexto2.padStart(MAX_CIFRAS, '0')
        }
        return Pair (primerValorCompleto, segundoValorCompleto)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        completarConCeros()

        binding.iconoRestaDM.setImageResource(R.mipmap.ic_menu_resta)
        binding.iconoIgualDM.setImageResource(R.mipmap.ic_igual)
        binding.iconoRestaUM.setImageResource(R.mipmap.ic_menu_resta)
        binding.iconoIgualUM.setImageResource(R.mipmap.ic_igual)
        binding.iconoRestaC.setImageResource(R.mipmap.ic_menu_resta)
        binding.iconoIgualC.setImageResource(R.mipmap.ic_igual)
        binding.iconoRestaD.setImageResource(R.mipmap.ic_menu_resta)
        binding.iconoIgualD.setImageResource(R.mipmap.ic_igual)
        binding.iconoRestaU.setImageResource(R.mipmap.ic_menu_resta)
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