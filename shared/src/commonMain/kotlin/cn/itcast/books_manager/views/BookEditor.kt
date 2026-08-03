package cn.itcast.books_manager.views

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import booksmanager.shared.generated.resources.Res
import booksmanager.shared.generated.resources.icon_back
import cn.itcast.books_manager.viewmodels.BookManagerViewModel
import cn.itcast.books_manager.views.components.NavigationTopBar

@Composable fun BookEditor(onBack: () -> Unit = {}, id: String?, vm: BookManagerViewModel = viewModel()) {
  val bookName = rememberTextFieldState("")
  val author = rememberTextFieldState("")
  val publisher = rememberTextFieldState("")

  val uiState by vm.uiState.collectAsState()

  LaunchedEffect(Unit) {
    if (id.isNullOrBlank()) return@LaunchedEffect // 如果id为空就跳出副作用区域
    vm.getBookDetail(id)
  }

  LaunchedEffect(uiState.detail) {
    if (uiState.detail == null) return@LaunchedEffect
    val detail = uiState.detail ?: return@LaunchedEffect
    bookName.setTextAndPlaceCursorAtEnd(detail.bookname)
    author.setTextAndPlaceCursorAtEnd(detail.author)
    publisher.setTextAndPlaceCursorAtEnd(detail.publisher)
  }

  Column(Modifier.fillMaxSize(), Arrangement.spacedBy(16.dp)) {
    NavigationTopBar(if (id?.isNotEmpty() ?: false) "编辑图书${id}页" else "新增图书页", onBack, {}, {}, Res.drawable.icon_back, showRightIcon = false)

    when(uiState.loading) {
      true -> {
        // 进度条
        Box(Modifier.fillMaxWidth().weight(1f), Alignment.Center) {
          CircularProgressIndicator()
        }
      }
      else -> {
        Column(Modifier.fillMaxWidth().padding(16.dp, 0.dp).padding(top = 8.dp), Arrangement.spacedBy(12.dp)) {
          BookEditorCeil("图书名称", bookName)
          BookEditorCeil("图书作者", author)
          BookEditorCeil("图书出版社", publisher)
        }

        Button({}, Modifier.fillMaxWidth().height(48.dp).padding(16.dp, 0.dp), colors = ButtonColors(
          containerColor = Color(0xFF000000),
          contentColor = Color(0xFF000000),
          disabledContainerColor = Color(0xFF000000),
          disabledContentColor = Color(0xFF000000)
        )) {
          Text("保存", color = Color(0xFFFFFFFF))
        }
      }
    }
  }
}

@Composable fun BookEditorCeil(label: String, value: TextFieldState, onValueChange: (value: String) -> Unit = {}) {
  Row(Modifier.fillMaxWidth().height(56.dp), verticalAlignment = Alignment.CenterVertically) {
    Text("${label}:")
    TextField(value, Modifier.weight(1f).border(0.dp, Color.Transparent), placeholder = {
      Text("请输入${label}")
    }, colors = TextFieldDefaults.colors(
      focusedContainerColor = Color.Transparent,
      unfocusedContainerColor = Color.Transparent,
      disabledContainerColor = Color.Transparent,
      focusedIndicatorColor = Color.Transparent,
      unfocusedIndicatorColor = Color.Transparent,
    ))
  }
  HorizontalDivider(Modifier.fillMaxWidth(), 0.5.dp, Color(0xFF333333))
}