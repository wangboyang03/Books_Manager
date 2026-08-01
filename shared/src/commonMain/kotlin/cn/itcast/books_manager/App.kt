package cn.itcast.books_manager

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.painterResource

import booksmanager.shared.generated.resources.Res
import booksmanager.shared.generated.resources.compose_multiplatform
import cn.itcast.books_manager.router.AppNavigationHost

@Composable @Preview fun App() {
  // 除了只读属性 控制器需要被记忆 避免重组时状态丢失 重新初始化
  val navController = rememberNavController()
  MaterialTheme {
    AppNavigationHost(navController)
  }
}