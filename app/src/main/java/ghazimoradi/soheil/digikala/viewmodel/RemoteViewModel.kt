package ghazimoradi.soheil.digikala.viewmodel

import androidx.lifecycle.ViewModel

abstract class RemoteViewModel : ViewModel() {
    abstract fun getAllDataFromServer()
}