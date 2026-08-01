package cn.itcast.books_manager.router

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cn.itcast.books_manager.views.BookEditor
import cn.itcast.books_manager.views.BookList

@Composable fun AppNavigationHost(controller: NavHostController) {
  NavHost(controller, RouterMap.BookList.route) {
    composable(RouterMap.BookList.route) {
      BookList({
        val targetRoute = if (it == null) {
          // 跳转并没有携带id过来 应该是去新增图书

        } else {
          // 携带图书id进行跳转 发送网络请求 修改图书参数
        }
        controller.navigate(RouterMap.BookEditor.route)
      })
    }
    composable(RouterMap.BookEditor.route) {
      BookEditor({
        controller.popBackStack()
      })
    }
  }
}