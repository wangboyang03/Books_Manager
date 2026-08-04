package cn.itcast.books_manager

import android.content.Context
import android.os.Build
import android.widget.Toast

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

// 延迟初始化
private lateinit var _context: Context

/**
 * 初始化上下文
 */
fun initContext(context: Context) {
  _context = context
}
actual fun ToastMessage(message: String) {
  Toast.makeText(_context, message, Toast.LENGTH_SHORT).show()
}