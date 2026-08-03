package cn.itcast.books_manager.views

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cn.itcast.books_manager.router.RouterMap

@Composable fun NavigationHost(controller: NavHostController) {
  NavHost(controller, RouterMap.BookListScreen.name) {
    composable(RouterMap.BookListScreen.name) {
      BookListScreen(controller)
    }
    composable(RouterMap.BookManagerScreen.name) {
      BookManagerScreen(controller)
    }
  }
}