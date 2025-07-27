package ghazimoradi.soheil.digikala.viewmodel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ghazimoradi.soheil.digikala.data.model.checkout.OrderFullDetail
import ghazimoradi.soheil.digikala.data.model.prfile.LoginRequest
import ghazimoradi.soheil.digikala.data.model.prfile.LoginResponse
import ghazimoradi.soheil.digikala.data.model.prfile.SetUserNameRequest
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.repository.ProfileRepository
import ghazimoradi.soheil.digikala.ui.screens.profile.ProfileScreenState
import ghazimoradi.soheil.digikala.util.Constants.USER_TOKEN
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : RemoteViewModel() {
    //sharedViewModel
    var screenState by mutableStateOf(ProfileScreenState.LOGIN_STATE)

    var inputPhoneState by mutableStateOf("")
    var inputPasswordState by mutableStateOf("")
    var loadingState by mutableStateOf(false)

    val loginResponse = MutableStateFlow<NetworkResult<LoginResponse>>(NetworkResult.Loading())

    val setUserNameResponse = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())

    val orderItems =
        MutableStateFlow<NetworkResult<List<OrderFullDetail>>>(NetworkResult.Loading())

    fun login() {
        viewModelScope.launch {
            loadingState = true
            val loginRequest = LoginRequest(inputPhoneState, inputPasswordState)
            loginResponse.emit(repository.login(loginRequest))
        }
    }

    fun refreshToken(phone: String, password: String){
        viewModelScope.launch {
            val loginRequest = LoginRequest(phone , password)
            loginResponse.emit(repository.login(loginRequest))
        }
    }

    fun setUserName (newUserName: SetUserNameRequest){
        viewModelScope.launch {
            setUserNameResponse.emit(repository.setUserName(newUserName))
        }
    }

    fun getUserOrders() {
        viewModelScope.launch {
            orderItems.emit(repository.getUserOrders(USER_TOKEN))
        }
    }

    override fun getAllDataFromServer() {
        getUserOrders()
    }
}