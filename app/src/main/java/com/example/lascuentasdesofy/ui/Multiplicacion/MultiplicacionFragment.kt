package com.example.lascuentasdesofy.ui.Multiplicacion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lascuentasdesofy.databinding.FragmentMultiplicacionBinding

class MultiplicacionFragment : Fragment() {

    private var _binding: FragmentMultiplicacionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val multiplicacionViewModel =
            ViewModelProvider(this).get(MultiplicacionViewModel::class.java)

        _binding = FragmentMultiplicacionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSlideshow
        multiplicacionViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}