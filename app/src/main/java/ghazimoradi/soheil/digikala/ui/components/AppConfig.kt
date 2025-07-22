package ghazimoradi.soheil.digikala.ui.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import ghazimoradi.soheil.digikala.viewmodel.DataStoreViewModel
import ghazimoradi.soheil.digikala.util.Constants.USER_LANGUAGE

@Composable
fun AppConfig(
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
) {
    getDataStoreVariables(dataStoreViewModel)
}

private fun getDataStoreVariables(
    dataStoreViewModel: DataStoreViewModel
) {
    USER_LANGUAGE = dataStoreViewModel.getUserLanguage()
    dataStoreViewModel.saveUserLanguage(USER_LANGUAGE)
}