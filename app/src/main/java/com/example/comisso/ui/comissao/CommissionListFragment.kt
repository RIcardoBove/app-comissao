package com.example.comisso.ui.comissao

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Dao
import com.example.comisso.R
import com.example.comisso.data.local.database.AppDatabase
import com.example.comisso.data.repository.CommissionRepository
import com.example.comisso.databinding.FragmentComissaoListBinding
import com.example.comisso.ui.comissao.adapter.CommissionAdapter
import com.example.comisso.ui.factory.CommissionListViewFactory
import com.example.comisso.ui.viewmodel.CommissionListViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale


class CommissionListFragment : Fragment() {

    private var _binding: FragmentComissaoListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CommissionListViewModel

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

        val database = AppDatabase.getDatabase(requireContext())

        val repository = CommissionRepository(
            database.commissionDao()
        )

        val factory = CommissionListViewFactory(repository)

        viewModel = ViewModelProvider(
            this,
            factory
        )[CommissionListViewModel::class.java]

        val recyclerView = binding.rvComissao

        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        lifecycleScope.launch {

            val today = LocalDate.now()
            val monthName = today.month.getDisplayName(
                TextStyle.FULL,
                Locale("pt", "BR")
            ).replaceFirstChar { it.uppercase() }

            binding.tvMonth.text = "$monthName ${today.year}"

            val startDate = today.withDayOfMonth(1)
            val endDate = today.withDayOfMonth(today.dayOfMonth)

            val commissions = viewModel.getCommissionsByDate(startDate, endDate)

            val adapter = CommissionAdapter(commissions)

            recyclerView.adapter = adapter
        }

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