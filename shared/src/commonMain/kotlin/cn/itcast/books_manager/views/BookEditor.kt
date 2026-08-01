package cn.itcast.books_manager.views

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import booksmanager.shared.generated.resources.Res
import booksmanager.shared.generated.resources.icon_back
import cn.itcast.books_manager.views.components.NavigationTopBar

@Composable fun BookEditor(onBack: () -> Unit = {}) {
  Column {
    NavigationTopBar("图书编辑页面", onBack, {}, {}, Res.drawable.icon_back, showRightIcon = false)
  }
}