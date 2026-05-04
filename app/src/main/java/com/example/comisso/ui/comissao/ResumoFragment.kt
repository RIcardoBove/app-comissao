package com.example.comisso.ui.comissao

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.comisso.R
import com.example.comisso.databinding.FragmentComissaoListBinding
import com.example.comisso.databinding.FragmentResumoBinding



class ResumoFragment : Fragment() {

    private var _binding: FragmentResumoBinding? = null

    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentResumoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}