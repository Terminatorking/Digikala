package ghazimoradi.soheil.digikala.viewModels

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ghazimoradi.soheil.digikala.data.models.purchase.PaymentRequest
import ghazimoradi.soheil.digikala.data.models.purchase.PaymentResponse
import ghazimoradi.soheil.digikala.data.models.purchase.PaymentVerificationRequest
import ghazimoradi.soheil.digikala.data.models.purchase.PaymentVerificationResponse
import ghazimoradi.soheil.digikala.repositories.PurchaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.core.net.toUri

@HiltViewModel
class PurchaseViewModel @Inject constructor(
    private val repository: PurchaseRepository
) : RemoteViewModel() {

    private val _purchaseResult = MutableStateFlow<PaymentResponse?>(null)
    val purchaseResult: StateFlow<PaymentResponse?> = _purchaseResult

    private val _verifyPurchaseResult = MutableStateFlow<PaymentVerificationResponse?>(null)
    val verifyPurchaseResult: StateFlow<PaymentVerificationResponse?> = _verifyPurchaseResult

    fun startPurchase(paymentRequest: PaymentRequest) {
        viewModelScope.launch {
            try {
                _purchaseResult.emit(repository.startPurchase(paymentRequest))
            } catch (e: Exception) {
                Log.e("PurchaseViewModel", "Exception occurred", e)
                _purchaseResult.emit(null)
            }
        }
    }

    fun verifyPurchase(paymentVerificationRequest: PaymentVerificationRequest) {
        viewModelScope.launch {
            try {
                _verifyPurchaseResult.emit(repository.verifyPurchase(paymentVerificationRequest))
            } catch (e: Exception) {
                Log.e("PurchaseViewModel", "Exception occurred", e)
                _verifyPurchaseResult.emit(null)
            }
        }
    }

    fun openBrowser(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }
}