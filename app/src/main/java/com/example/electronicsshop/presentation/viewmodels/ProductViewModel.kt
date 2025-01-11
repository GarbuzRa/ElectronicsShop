package com.example.electronicsshop.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.electronicsshop.domain.interactor.ProductsInteractor
import com.example.electronicsshop.domain.model.ProductDomain
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

sealed class State {
    object Loading: State()
    object Empty: State()
    data class Error(val message: String): State()
    data class Success(val list: List<ProductDomain>): State()
}

class ProductViewModel(val interactor: ProductsInteractor): ViewModel() {
    val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state
    private var searchJob: Job? = null

    fun getAllProducts() {
        viewModelScope.launch {
            _state.value = State.Loading
            interactor.getProducts().collect {result ->
                result.fold(
                    onSuccess = {list ->
                        if (list.isNullOrEmpty() == true) {
                            _state.value = State.Empty
                        } else {
                            _state.value = State.Success(list)
                        }
                    },
                    onFailure = {e ->
                        _state.value = State.Error("Ошибка")
                    }
                )
            }
        }
    }
  /*  fun searchProduct(name: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _state.value = State.Loading
            interactor.searchProduct(name).collect {result ->
                result.fold(
                    onSuccess = {list ->
                        if (list.isNullOrEmpty() == true) {
                            _state.value = State.Empty
                        } else {
                            _state.value = State.Success(list)
                        }
                    },
                    onFailure = { e ->
                        _state.value = State.Error("Нет инета")

                    }
                )
            }
        }
    } */
}