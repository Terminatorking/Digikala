package ghazimoradi.soheil.digikala.ui.components.extentions

fun String.removeAllSpaces(): String {
   return this.replace("\\s".toRegex(), "")
}