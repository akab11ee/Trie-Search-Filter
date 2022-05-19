package com.example.citysearchapp.ui.city

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.citysearchapp.R
import com.example.citysearchapp.data.entity.City
import com.example.citysearchapp.databinding.ItemCityBinding
import com.example.citysearchapp.ui.city.interfaces.IOnItemClickListener

/**
 * @Author: Akash Abhishek
 * @Date: 19 May 2022
 */

class CityAdapter :
    RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    private var listCity: MutableList<City> = mutableListOf()
    lateinit var onItemClickListener: IOnItemClickListener
    fun updateItems(items: List<City>) {
        items.forEach { this.listCity.add(it) }
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.listCity.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount() = listCity.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CityViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_city,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.binding.item = listCity[position]
        holder.binding.searchItem.setOnClickListener {
            onItemClickListener.onItemClick(listCity[position])
        }

    }

    class CityViewHolder(val binding: ItemCityBinding) :
        RecyclerView.ViewHolder(binding.root)
}