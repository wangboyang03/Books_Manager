package cn.itcast.books_manager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import cn.itcast.books_manager.views.NavigationHost

@Composable fun App() {
  MaterialTheme {
    val navController = rememberNavController()
    Column(Modifier.background(MaterialTheme.colorScheme.primaryContainer).safeContentPadding().fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
      NavigationHost(navController)
    }
  }
}