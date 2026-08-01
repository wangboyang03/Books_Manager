package cn.itcast.books_manager

class Greeting {
  private val platform = getPlatform()
  fun greet(): String {
    return sayHello(platform.name)
  }
}