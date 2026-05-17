package com.example.comisso.ui.comissao.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.comisso.data.local.entity.CommissionEntity
import com.example.comisso.databinding.ItemCommissionBinding

class CommissioAdapter(private val commissions: List<CommissionEntity>) :
    RecyclerView.Adapter<CommissioAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCommissionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return commissions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val commission = commissions[position]

        holder.binding.tvService.text = commission.service
        holder.binding.tvCar.text = commission.car
        holder.binding.tvDate.text = commission.date
        holder.binding.tvValue.text = "R$ ${commission.value}"

    }

    class ViewHolder(val binding: ItemCommissionBinding) : RecyclerView.ViewHolder(binding.root)

}