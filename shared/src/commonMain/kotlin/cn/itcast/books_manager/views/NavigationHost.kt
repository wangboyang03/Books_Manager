package cn.itcast.books_manager.views

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import cn.itcast.books_manager.router.RouterMap

@Composable fun NavigationHost(controller: NavHostController) {
  NavHost(controller, RouterMap.BookListScreen.name) {
    composable(RouterMap.BookListScreen.name) {
      BookListScreen({
        val routeTarget = when(it) {
          null -> {
            // 跳转到新增页
            RouterMap.BookManagerScreen.name
          }
          else -> {
            "${RouterMap.BookManagerScreen.name}?id=$it"
          }
        }
        controller.navigate(routeTarget)
      })
    }
    composable("${RouterMap.BookManagerScreen.name}?id={id}", listOf(navArgument("id"){
      type = NavType.StringType
      nullable = true
      defaultValue = null
    })) {
      val params = it.arguments?.getString("id")
      BookManagerScreen(params, {
        controller.popBackStack()
      })
    }
  }
}