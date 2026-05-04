package com.example.comisso.ui.comissao

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.comisso.R
import com.example.comisso.databinding.FragmentComissaoListBinding


class ComissaoListFragment : Fragment() {

    private var _binding: FragmentComissaoListBinding? = null

    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentComissaoListBinding.inflate(inflater, container, false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAddComissao.setOnClickListener {
            Log.i("ComissaoListFragment", "Clicou no FAB")
            findNavController().navigate(R.id.action_list_to_form)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}