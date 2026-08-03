package cn.itcast.books_manager

import android.content.Context
import android.os.Build
import android.widget.Toast

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()


lateinit var _context: Context

fun initContext(context: Context) {
  _context = context
}

actual object ToastMessage {
  actual fun openToast(message: String) {
    Toast.makeText(_context, message, Toast.LENGTH_LONG).show()
  }
}