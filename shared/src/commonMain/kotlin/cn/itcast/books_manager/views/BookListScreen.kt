package cn.itcast.books_manager.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import cn.itcast.books_manager.views.components.NavigationBar

@Composable fun BookListScreen(navController: NavController) {
  Column(Modifier.fillMaxSize().background(Color.Transparent)) {
    NavigationBar("图书列表")
  }
}