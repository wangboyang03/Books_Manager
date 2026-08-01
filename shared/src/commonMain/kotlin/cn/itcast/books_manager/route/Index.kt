package cn.itcast.books_manager.route

sealed class RouterMap(val route: String) {
  object BookList : RouterMap("book_list")
  object BookEditor : RouterMap("book_editor")
}