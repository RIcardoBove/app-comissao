package com.example.comisso.ui.comissao

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.comisso.R
import com.example.comisso.data.local.database.AppDatabase
import com.example.comisso.data.local.entity.CommissionEntity
import com.example.comisso.data.repository.CommissionRepository
import com.example.comisso.databinding.FragmentComissaoListBinding
import com.example.comisso.ui.comissao.adapter.CommissionAdapter
import com.example.comisso.ui.factory.CommissionListViewFactory
import com.example.comisso.ui.viewmodel.CommissionListViewModel
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale


class CommissionListFragment : Fragment() {

    private var _binding: FragmentComissaoListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CommissionListViewModel
    private val selectedMonth = LocalDate.now()
    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var adapter: CommissionAdapter
    private lateinit var gestureDetector: GestureDetectorCompat

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

        gestureDetector = GestureDetectorCompat(
            requireContext(),
            object : GestureDetector.SimpleOnGestureListener() {

                override fun onFling(
                    e1: MotionEvent?,
                    e2: MotionEvent,
                    velocityX: Float,
                    velocityY: Float
                ): Boolean {
                    Log.d("Delsizar", "Detected fling")
                    return true
                }
            }
        )

        binding.root.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }


        val database = AppDatabase.getDatabase(requireContext())

        val repository = CommissionRepository(
            database.commissionDao()
        )

        val factory = CommissionListViewFactory(repository)

        viewModel = ViewModelProvider(
            this,
            factory
        )[CommissionListViewModel::
        class.java]

        recyclerView = binding.rvComissao
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            loadMonth(selectedMonth)
        }

        binding.fabAddComissao.setOnClickListener {
            findNavController().navigate(R.id.action_list_to_form)
        }
    }

    private suspend fun loadMonth(month: LocalDate) {

        val monthName = month.month.getDisplayName(
            TextStyle.FULL,
            Locale("pt", "BR")
        ).replaceFirstChar { it.uppercase() }

        binding.tvMonth.text = "$monthName ${month.year}"

        val startDate = month.withDayOfMonth(1)
        val endDate = month.withDayOfMonth(month.lengthOfMonth())

        val commissions = viewModel.getCommissionsByDate(startDate, endDate)
        adapter = CommissionAdapter(commissions) { commission ->
            showDeleteConfirmationDialog(commission)
        }

        //calculate total and format it
        val total = viewModel.calculateTotal(commissions)
        val totalFormatted = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(total)
        binding.tvTotalMonth.text = totalFormatted

        recyclerView.adapter = adapter
    }

    private fun showDeleteConfirmationDialog(commission: CommissionEntity) {
        AlertDialog.Builder(requireContext())
            .setTitle("Excluir Comissão")
            .setMessage("Tem certeza que deseja excluir esta comissão?")
            .setPositiveButton("Sim") { _, _ ->
                viewModel.deleteCommission(commission)
                lifecycleScope.launch {
                    loadMonth(selectedMonth)
                }
            }
            .setNegativeButton("Não", null)
            .show()
    }


override fun onDestroy() {
    super.onDestroy()
    _binding = null
}



}