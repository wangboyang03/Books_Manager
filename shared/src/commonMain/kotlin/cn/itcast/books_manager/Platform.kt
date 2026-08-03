package cn.itcast.books_manager

interface Platform {
  val name: String
}

expect fun getPlatform(): Platform

expect object ToastMessage {
  fun openToast(message: String)
}