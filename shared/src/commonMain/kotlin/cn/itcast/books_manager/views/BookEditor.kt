package cn.itcast.books_manager.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cn.itcast.books_manager.route.RouterMap

@Composable fun BookEditor() {
  Box(Modifier.fillMaxSize(), Alignment.Center) {
    Text("图书编辑页面", fontSize = 40.sp, fontWeight = FontWeight.Bold)
  }
}