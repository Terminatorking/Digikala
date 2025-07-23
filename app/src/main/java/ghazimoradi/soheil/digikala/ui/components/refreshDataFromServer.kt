package ghazimoradi.soheil.digikala.ui.components

import ghazimoradi.soheil.digikala.viewmodel.RemoteViewModel

fun refreshDataFromServer(viewModel: RemoteViewModel) {
    viewModel.getAllDataFromServer()
}