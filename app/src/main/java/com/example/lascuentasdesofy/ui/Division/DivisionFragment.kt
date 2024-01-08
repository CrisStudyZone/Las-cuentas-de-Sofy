package com.example.lascuentasdesofy.ui.Division

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lascuentasdesofy.databinding.FragmentDivisionBinding
import com.example.lascuentasdesofy.ui.Multiplicacion.DivisionViewModel

class DivisionFragment : Fragment() {

    private var _binding: FragmentDivisionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val divisionViewModel =
            ViewModelProvider(this).get(DivisionViewModel::class.java)

        _binding = FragmentDivisionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSlideshow
        divisionViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}