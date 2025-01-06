package com.example.electronicsshop.presentation.ui.catalog

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.electronicsshop.databinding.ItemProductBinding
import com.example.electronicsshop.domain.model.ProductDomain

class ProductsViewHolder(val binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(product: ProductDomain) {
        binding.productName.text = product.name
        binding.productDescription.text = product.description
        binding.productPrice.text = "$${product.price}"
    }
}