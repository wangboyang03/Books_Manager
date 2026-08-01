package cn.itcast.books_manager

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import cn.itcast.books_manager.router.AppNavigationHost

@Composable @Preview fun App() {
  // 除了只读属性 控制器需要被记忆 避免重组时状态丢失 重新初始化
  val navController = rememberNavController()
  MaterialTheme {
    AppNavigationHost(navController)
  }
}