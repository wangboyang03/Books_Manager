package cn.itcast.books_manager.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable fun BookManagerScreen(navController: NavController) {
  Box(Modifier.fillMaxSize(), Alignment.Center) {
    Text("图书管理页面")
  }
}