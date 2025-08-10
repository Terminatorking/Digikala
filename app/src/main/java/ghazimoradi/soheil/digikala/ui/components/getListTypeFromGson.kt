package ghazimoradi.soheil.digikala.ui.components

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T : Any> getListTypeFromGson(jsonString: String) : List<T> {
    val priceListType = object : TypeToken<List<T>>() {}.type
    return Gson().fromJson(jsonString, priceListType)
}