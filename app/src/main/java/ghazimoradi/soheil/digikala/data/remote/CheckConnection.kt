package ghazimoradi.soheil.digikala.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object CheckConnection {

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (connectivityManager != null) {
            val network = connectivityManager.activeNetwork
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            return networkCapabilities != null &&
                    (hasWifiConnection(networkCapabilities) || hasSimCardConnection(networkCapabilities))
        }

        return false
    }

    private fun hasWifiConnection(networkCapabilities: NetworkCapabilities): Boolean {
        return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
    }

    private fun hasSimCardConnection(networkCapabilities: NetworkCapabilities): Boolean {
        return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
    }
}