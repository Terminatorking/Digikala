package ghazimoradi.soheil.digikala.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ghazimoradi.soheil.digikala.repository.BasketRepository
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    val repository: BasketRepository
) : ViewModel() {
}