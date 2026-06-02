package com.example.comisso.ui.comissao

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.comisso.data.local.database.AppDatabase
import com.example.comisso.data.local.entity.CommissionEntity
import com.example.comisso.databinding.FragmentComissaoFormBinding
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.TimeZone
import androidx.navigation.fragment.findNavController

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

        val db = AppDatabase.getDatabase(requireContext())
        val dao = db.commissionDao()

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

        //Código para lista de serviços na dropdown
        data class Service(
            val name: String,
            val price: Double
        )

        val services = listOf(
            Service("Oleo de Câmbio Automatico", 100.0),
            Service("Limpeza de Injeção", 35.0),
            Service("Troca de Pastilhas", 30.0),
            Service("Troca de disco ou Faceamento", 25.0),
            Service("Limpeza fluido direção", 30.0),
            Service("Sangria de freios", 30.0),
            Service("Troca liquido arrefecimento", 40.0),
            Service("Filtro de Cabine e Higenização", 15.0),
            Service("Militec", 10.0)
        )

        val serviceNames = services.map { it.name }.toMutableList()
        serviceNames.add("Outro")

        val adapterService = ArrayAdapter(
            requireContext(),
            R.layout.simple_dropdown_item_1line,
            serviceNames
        )

        val dropDownService = binding.dropdownServico

        dropDownService.setAdapter(adapterService)

        dropDownService.setOnClickListener {
            dropDownService.showDropDown()
        }

        dropDownService.setOnItemClickListener { _, _, position, _ ->

            val selectedService = serviceNames[position]

            if (selectedService == "Outro") {

                binding.layoutNovoServico.visibility = View.VISIBLE
                binding.etValor.setText("")

            } else {

                val service = services.first {
                    it.name == selectedService
                }

                binding.etValor.setText(
                    service.price.toString()
                )

                binding.layoutNovoServico.visibility = View.GONE
            }
        }
        binding.btnSalvar.setOnClickListener {
            val date = binding.etDate.text.toString()
            val car = binding.dropdownCar.text.toString()
            val service = binding.dropdownServico.text.toString()
            val value = binding.etValor.text.toString().toDoubleOrNull() ?: 0.0

            val formater = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val dateFormatted = LocalDate.parse(date, formater)

            val isCustomService = service == "Outro"

            val finalService = if (isCustomService) {
                binding.etNovoServico.text.toString()
            } else {
                service
            }

            val commission = CommissionEntity(
                date = dateFormatted,
                car = car,
                service = finalService,
                value = value,
                isCustomService = isCustomService
            )

            lifecycleScope.launch {
                dao.insert(commission)
            }

            Toast.makeText(requireContext(), "Salvo com sucesso", Toast.LENGTH_SHORT).show()

            findNavController().popBackStack()
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

            val utcCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))

            utcCalendar.timeInMillis = selection

            val day = utcCalendar.get(Calendar.DAY_OF_MONTH)

            val month = utcCalendar.get(Calendar.MONTH) + 1

            val year = utcCalendar.get(Calendar.YEAR)

            val formattedDate =
                String.format("%02d/%02d/%d", day, month, year)

            binding.etDate.setText(formattedDate)
        }
    }


}