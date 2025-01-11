package com.example.electronicsshop.presentation.ui.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.electronicsshop.databinding.FragmentCatalogBinding
import com.example.electronicsshop.presentation.viewmodels.ProductViewModel
import com.example.electronicsshop.presentation.viewmodels.State
import org.koin.androidx.viewmodel.ext.android.viewModel

class CatalogFragment : Fragment() {
    private lateinit var binding: FragmentCatalogBinding
    private val viewModel by viewModel<ProductViewModel>()
    private lateinit var adapter: ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatalogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllProducts()
    }

    fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) {product ->
            when(product) {
                is State.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is State.Empty -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                    binding.errorMsg.visibility = View.GONE
                }
                is State.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    adapter.updateProducts(product.list.toMutableList())
                }
                is State.Error -> {
                    binding.errorMsg.visibility = View.VISIBLE
                }
            }
        }
    }

    fun setupAdapter() {
        adapter = ProductsAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}