package com.example.lascuentasdesofy.core

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.lascuentasdesofy.R
import com.example.lascuentasdesofy.databinding.DialogFragmentTablaPitagoricaBinding

class TablaPtagoricaDialogFragment : DialogFragment() {

    private var _binding: DialogFragmentTablaPitagoricaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DialogFragmentTablaPitagoricaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Accedo a las vistas a través del objeto de enlace
        val gridLayoutTablaPitagoricaBinding = binding.gridLayoutTablaPitagorica
        val gridLayoutMarcoHorizontalBinding = binding.gridlayoutMarcoHorizontal
        val gridLayoutMarcoVerticalBinding = binding.gridlayoutMarcoVertical

        // Defino el tamaño de la matriz
        val rows = 11
        val columns = 11

        // Creo encabezados de fila (números del 0 al 10)
        for (row in 1..rows) {
            val headerButton = createTextButton(row-1)
            headerButton.setBackgroundResource(R.drawable.first_button_background) // Cambia el color según tu preferencia
            gridLayoutMarcoVerticalBinding.addView(headerButton)

            // Configuro parámetros de diseño para GridLayout
            val params = GridLayout.LayoutParams()
            params.rowSpec = GridLayout.spec(row-1)
            params.columnSpec = GridLayout.spec(0)
            headerButton.layoutParams = params//Aca me tira el error

            // Configuro OnClickListener para cambiar el color de la fila
            headerButton.setOnClickListener {
                val selectValue = headerButton.text.toString().toInt()

                //Entiendo que aca esta el problema, pero no lo se
                for (innerColumn in 1 until columns) {
                    if (innerColumn == selectValue){
                        for (index in 0 until gridLayoutTablaPitagoricaBinding.rowCount-1) {
                            val button = gridLayoutTablaPitagoricaBinding.getChildAt(index * columns + selectValue) as? TextView
                            button?.setBackgroundColor(R.drawable.first_button_background) // Cambio la configuracion (esto tampoco funciona como quiero pero tampoco lo investigue mucho
                        }
                    }else{
                        continue
                    }
                }
            }
        }


        // Creo encabezados de columna (números del 0 al 12)
        for (column in 1..columns) {
            val headerButton = createTextButton(column-1)
            headerButton.setBackgroundResource(R.drawable.first_button_background) // Cambia el color según tu preferencia
            gridLayoutMarcoHorizontalBinding.addView(headerButton)

            // Configurar parámetros de diseño para GridLayout
            val params = GridLayout.LayoutParams()
            params.rowSpec = GridLayout.spec(0)
            params.columnSpec = GridLayout.spec(column-1)
            headerButton.layoutParams = params

            // Configurar OnClickListener para cambiar el color de la columna
            headerButton.setOnClickListener {
                val selectValue = headerButton.text.toString().toInt()

                for (innerRow in 1..rows) {
                    if (innerRow == selectValue){
                        for (index in 0..gridLayoutTablaPitagoricaBinding.columnCount) {
                            val button = gridLayoutTablaPitagoricaBinding.getChildAt(selectValue * rows + index) as? TextView
                            button?.setBackgroundColor(R.drawable.first_button_background) // Cambia el color según tu preferencia
                        }
                    }else{
                        continue
                    }
                }
            }
        }


        // Itera para crear botones con los productos resultantes y agregarlos a la matriz
        for (row in 1..rows) {
            for (column in 1..columns) {
                val producto = (row-1) * (column-1)
                val textButton = createTextButton(producto)
                gridLayoutTablaPitagoricaBinding.addView(textButton)

                // Configura parámetros de diseño para GridLayout
                val params = GridLayout.LayoutParams()
                params.rowSpec = GridLayout.spec(row-1)
                params.columnSpec = GridLayout.spec(column-1)
                textButton.layoutParams = params
            }
        }
        return root
    }

    private fun createTextButton(value: Int): TextView {
        val textButton = TextView(requireContext())
        textButton.text = value.toString()
        textButton.textSize = 12f
        textButton.setTextColor(Color.WHITE)
        textButton.gravity = Gravity.CENTER
        textButton.setBackgroundResource(R.drawable.button_background)
        textButton.setOnClickListener(onClickListener)
        return  textButton
    }


    // Manejador de clic para los botones
    private val onClickListener = View.OnClickListener { view ->
        // Acciones cuando se hace clic en un botón
        val buttonText = (view as Button).text.toString()
        // Puedes realizar acciones adicionales aquí
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}