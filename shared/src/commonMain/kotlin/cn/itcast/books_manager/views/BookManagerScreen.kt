package cn.itcast.books_manager.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import booksmanager.shared.generated.resources.Res
import booksmanager.shared.generated.resources.icon_checkmark
import booksmanager.shared.generated.resources.icon_popback
import cn.itcast.books_manager.ToastMessage
import cn.itcast.books_manager.models.BookDatumRequest
import cn.itcast.books_manager.viewmodels.BookManagerEvent
import cn.itcast.books_manager.viewmodels.BookManagerViewModel
import cn.itcast.books_manager.views.components.FormItem
import cn.itcast.books_manager.views.components.NavigationBar
import kotlinx.coroutines.launch

@Composable fun BookManagerScreen(id: String?, onBackPage: () -> Unit = {}, vm: BookManagerViewModel = viewModel()) {
  val titleName = when (id) {
    null -> "请输入您想要新增的图书信息"
    else -> "您现在正在修改${id}图书信息"
  }

  var bookName by remember { mutableStateOf("") }
  var author by remember { mutableStateOf("") }
  var publisher by remember { mutableStateOf("") }
  val bookManagerState by vm.bookManagerState.collectAsState()

  LaunchedEffect(Unit) {
    if (id.isNullOrEmpty()) return@LaunchedEffect
    vm.getCurrentBookDatum(id)
  }

  LaunchedEffect(bookManagerState.currentBookDatum) {
    if (bookManagerState.currentBookDatum == null) return@LaunchedEffect
    val bookDatum = bookManagerState.currentBookDatum ?: return@LaunchedEffect
    bookName = bookDatum.bookname
    author = bookDatum.author
    publisher = bookDatum.publisher
  }

  val scope = rememberCoroutineScope()

  LaunchedEffect(vm) {
    vm.event.collect {
      when(it) {
        is BookManagerEvent.SaveEvent -> {
          when(it.isSuccess) {
            true -> onBackPage() // 保存成功 回到上一页
            else -> ToastMessage("保存失败")
          }
        }
      }
    }
  }

  Column(Modifier.fillMaxSize(), Arrangement.spacedBy(10.dp)) {
    NavigationBar(titleName, Res.drawable.icon_popback, Res.drawable.icon_checkmark, onLiftClick = onBackPage, onRightClick = {
      scope.launch {
        vm.savedCurrentBookDatum(BookDatumRequest(author, bookName, "wangbadan", publisher), id)
      }
    },)
    Spacer(Modifier.height(16.dp))
    FormItem(label = "图书名称", value = bookName, onValueChange = { bookName = it }, placeholder = "请输入图书名称")
    FormItem(label = "图书作者", value = author, onValueChange = { author = it }, placeholder = "请输入作者")
    FormItem(label = "出版社", value = publisher, onValueChange = { publisher = it }, placeholder = "请输入出版社")
  }
}