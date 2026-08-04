package cn.itcast.books_manager.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable fun BookManagerScreen(id: String?, onBackPage: () -> Unit = {}) {
  Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
    Text("图书管理页面$id")
    Button(onBackPage) {
      Text("返回上一页")
    }
  }
}