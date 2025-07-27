package ghazimoradi.soheil.digikala.util

import android.util.Patterns
import androidx.core.text.isDigitsOnly

object InputValidation {

    fun isValidPhoneNumber(input: String): Boolean {
        return input.isNotEmpty()
                && input.isNotBlank()
                && input.isDigitsOnly()
                && input.startsWith("09")
                && input.length == 11
    }

    fun isValidEmail(input: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(input).matches()
    }

    fun isValidPassword(input: String): Boolean {
        return input.isNotEmpty() && input.isNotBlank() && input.length >= 6
    }
}