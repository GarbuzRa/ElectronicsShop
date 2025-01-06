package com.example.electronicsshop.presentation.ui.catalog

import android.renderscript.ScriptGroup.Binding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.electronicsshop.data.model.Product
import com.example.electronicsshop.databinding.ItemProductBinding
import com.example.electronicsshop.domain.model.ProductDomain

class ProductsAdapter: RecyclerView.Adapter<ProductsViewHolder>() {
    var products = listOf<ProductDomain>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(products[position])
    }

    fun updateProducts(products: MutableList<ProductDomain>) {
        this.products = products
        notifyDataSetChanged()
    }
}