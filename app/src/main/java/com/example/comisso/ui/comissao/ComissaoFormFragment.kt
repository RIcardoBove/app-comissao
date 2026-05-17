package com.example.comisso.ui.comissao

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.comisso.databinding.FragmentComissaoFormBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class ComissaoFormFragment : Fragment() {

    private var _binding: FragmentComissaoFormBinding? = null

    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentComissaoFormBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etDate.setOnClickListener {
            abrirDatePicker()
        }

        //Código para lista de carros na dropdown
        val cars = listOf(
            "Gol",
            "Onix",
            "HB20",
            "Civic",
            "Corolla"
        )

        val adapterCars = ArrayAdapter(
            requireContext(),
            R.layout.simple_dropdown_item_1line,
            cars
        )

        val dropDownCar = binding.dropdownCar

        dropDownCar.setAdapter(adapterCars)

        dropDownCar.setOnClickListener {
            dropDownCar.showDropDown()
        }
        val typingCar = dropDownCar.text.toString()


        //Código para lista de serviços na dropdown
        val service = listOf(
            "Limpeza de Injeção",
            "Troca de Pastilhas",
            "Limpeza fluido direção",
            "Sangria de freios",
            "Outro"
        )

        val adpterService = ArrayAdapter(
            requireContext(),
            R.layout.simple_dropdown_item_1line,
            service
        )

        val dropDownService = binding.dropdownServico

        dropDownService.setAdapter(adpterService)

        dropDownService.setOnClickListener {
            dropDownService.showDropDown()
        }

        dropDownService.setOnItemClickListener { _, _, position, _ ->

            val select = service[position]

            if (select == "Outro") {
                binding.layoutNovoServico.visibility = View.VISIBLE

                binding.etValor.setText("")
                binding.etValor.isEnabled = true

            } else {
                val valor = when (select) {
                    "Limpeza de Injeção" -> "35"
                    "Troca de Pastilhas" -> "30"
                    "Limpeza fluido direção" -> "30"
                    "Sangria de freios" -> "30"
                    else -> ""
                }
                binding.etValor.setText(valor)
                binding.etValor.isEnabled = false

                binding.layoutNovoServico.visibility = View.GONE
            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun abrirDatePicker() {
        val datePicker = com.google.android.material.datepicker.MaterialDatePicker.Builder
            .datePicker()
            .setTitleText("Selecione a data")
            .build()

        datePicker.show(parentFragmentManager, "DATE_PICKER_TAG")

        datePicker.addOnPositiveButtonClickListener { selection ->

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val dataFormatada = sdf.format(Date(selection))

            binding.etDate.setText(dataFormatada)
        }
    }


}