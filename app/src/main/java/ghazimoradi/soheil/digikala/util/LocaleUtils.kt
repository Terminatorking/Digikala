package ghazimoradi.soheil.digikala.util

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

object LocaleUtils {

    fun setLocale(context: Context, language: String) = updateResource(context, language)

    private fun updateResource(context: Context, language: String) {
        context.resources.apply {
            //this -> resources
            val locale = Locale(language)
            val config = Configuration(this.configuration)

            context.createConfigurationContext(this.configuration)
            Locale.setDefault(locale)
            config.setLocale(locale)
            this.updateConfiguration(config, this.displayMetrics)
        }
    }
}