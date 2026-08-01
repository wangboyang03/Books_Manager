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
      BookList()
    }
    composable(RouterMap.BookEditor.route) {
      BookEditor()
    }
  }
}