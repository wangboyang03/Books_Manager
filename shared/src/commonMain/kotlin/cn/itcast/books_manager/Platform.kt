package cn.itcast.books_manager

interface Platform {
  val name: String
}

expect fun getPlatform(): Platform