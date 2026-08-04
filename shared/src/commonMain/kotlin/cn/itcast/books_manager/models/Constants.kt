package cn.itcast.books_manager.models

object Constants {
  const val BASE_URL: String = "https://hmajax.itheima.net/" // 基地址
  const val TIME_OUT: Long = 10000L // 超时时间,十秒
  // 页面路径常量
  const val BOOK_LIST_SCREEN_PATH = "book_list_screen"
  const val BOOK_MANAGER_SCREEN_PATH = "book_manager_screen"

  // 接口地址常量
  const val BOOK_DATUM_API = "api/books" // 图书信息接口
}