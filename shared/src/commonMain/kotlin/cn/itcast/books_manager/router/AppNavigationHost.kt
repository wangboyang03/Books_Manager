package cn.itcast.books_manager.router

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import cn.itcast.books_manager.views.BookEditor
import cn.itcast.books_manager.views.BookList

@Composable fun AppNavigationHost(controller: NavHostController) {
  NavHost(controller, RouterMap.BookList.route) {
    composable(RouterMap.BookList.route) {
      BookList({
        val targetRoute = when(it) {
          null -> RouterMap.BookEditor.route
          else -> "${RouterMap.BookEditor.route}?id=$it"
        }
        controller.navigate(targetRoute)
      })
    }
    composable("${RouterMap.BookEditor.route}?id={id}", listOf(navArgument("id"){
      type = NavType.StringType
      nullable = true
    })) {
      val param = it.arguments?.getString("id")
      BookEditor({
        controller.popBackStack()
      }, param)
    }
  }
}