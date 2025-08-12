package ghazimoradi.soheil.digikala.ui.components.extentions

import android.content.Context
import android.widget.Toast

fun CharSequence.showToast(context: Context, duration: Int = Toast.LENGTH_LONG) =
    Toast.makeText(context, this, duration).show()