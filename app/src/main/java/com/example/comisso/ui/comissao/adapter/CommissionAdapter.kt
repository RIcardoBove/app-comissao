package com.example.comisso.ui.comissao.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.comisso.data.local.entity.CommissionEntity
import com.example.comisso.data.local.relation.CommissionWithServices
import com.example.comisso.databinding.ItemCommissionBinding
import java.text.NumberFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

class CommissionAdapter(
    private val commissions: List<CommissionWithServices>,
    private val onDeleteClick: (CommissionEntity) -> Unit) :
    RecyclerView.Adapter<CommissionAdapter.ViewHolder>() {

    private val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCommissionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return commissions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val commissionWithServices = commissions[position]

        val commission = commissionWithServices.commission
        val services = commissionWithServices.services

        holder.binding.tvDate.text =
            commission.date.format(dateFormatter)

        // Mostra todos os serviços
        val servicesText = services.joinToString("\n") { service ->
            "${service.service} - ${
                NumberFormat
                    .getCurrencyInstance(Locale("pt", "BR"))
                    .format(service.value)
            }"
        }

        holder.binding.tvService.text = servicesText

        // Soma os serviços daquele carro
        val total = services.sumOf { service ->
            service.value
        }

        holder.binding.tvValue.text =
            NumberFormat
                .getCurrencyInstance(Locale("pt", "BR"))
                .format(total)

        holder.binding.ivDelete.setOnClickListener {
            onDeleteClick(commission)
        }
    }

    class ViewHolder(val binding: ItemCommissionBinding) : RecyclerView.ViewHolder(binding.root)

}