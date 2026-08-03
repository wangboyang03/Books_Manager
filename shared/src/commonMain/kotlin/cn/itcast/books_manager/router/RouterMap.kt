package cn.itcast.books_manager.router

import cn.itcast.books_manager.models.Constants

sealed class RouterMap(val name: String) {
  // 图书列表页
  object BookListScreen: RouterMap(Constants.BOOK_LIST_SCREEN_PATH)
  // 图书管理页
  object BookManagerScreen: RouterMap(Constants.BOOK_MANAGER_SCREEN_PATH)
}