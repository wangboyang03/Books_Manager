package cn.itcast.books_manager.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import booksmanager.shared.generated.resources.Res
import booksmanager.shared.generated.resources.book_1
import booksmanager.shared.generated.resources.book_2
import booksmanager.shared.generated.resources.book_3
import booksmanager.shared.generated.resources.book_4
import booksmanager.shared.generated.resources.book_5
import booksmanager.shared.generated.resources.book_6
import booksmanager.shared.generated.resources.book_7
import booksmanager.shared.generated.resources.book_8
import booksmanager.shared.generated.resources.book_9
import booksmanager.shared.generated.resources.icon_list
import booksmanager.shared.generated.resources.icon_picture
import cn.itcast.books_manager.viewmodels.BookListViewModel
import cn.itcast.books_manager.views.components.NavigationBar
import cn.itcast.books_manager.views.components.SwipeToDelete
import cn.itcast.books_manager.views.components.WaterFlow
import cn.itcast.books_manager.views.components.WaterFlowCard
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

private val bookImages = listOf(
  Res.drawable.book_1,
  Res.drawable.book_2,
  Res.drawable.book_3,
  Res.drawable.book_4,
  Res.drawable.book_5,
  Res.drawable.book_6,
  Res.drawable.book_7,
  Res.drawable.book_8,
  Res.drawable.book_9,
)

/** 随机图片辅助函数 **/
fun randomBookImage(): DrawableResource = bookImages.random()

@Composable
fun BookCard(bookname: String, author: String, publisher: String, image: DrawableResource = randomBookImage(), onClick: () -> Unit = {}) {
  Column(Modifier.fillMaxWidth().clickable{ onClick() }.padding(10.dp)) {
    Image(painterResource(image), null, Modifier.fillMaxWidth().height(200.dp).clip(RoundedCornerShape(12.dp)), contentScale = ContentScale.Crop)
    Text(bookname, Modifier.padding(top = 8.dp), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF23233C), maxLines = 2, overflow = TextOverflow.Ellipsis)
    Text(author, Modifier.padding(top = 4.dp), fontSize = 13.sp, color = Color(0xFF9E9EB8), maxLines = 1, overflow = TextOverflow.Ellipsis)
    Text(publisher, Modifier.padding(top = 4.dp), fontSize = 13.sp, color = Color(0xFF9E9EB8), maxLines = 1, overflow = TextOverflow.Ellipsis)
  }
}

@Composable fun BookListScreen(goToManagerPage: (id: String?) -> Unit = {}, vm: BookListViewModel = viewModel()) {

  val bookListState by vm.bookListState.collectAsState()
  var showConfirmDialog by remember { mutableStateOf(false) }
  var currentDeletingId by remember { mutableStateOf("") }
  var isWaterFlow by remember { mutableStateOf(false) }

  LaunchedEffect(Unit) {
    vm.getBookDatumList("wangbadan")
  }

  // 每本图书的瀑布流图片高度，按 id 取模得到不同高度，形成错落效果
  val waterfallImageHeight: (Int) -> Dp = { id ->
    when (id % 3) {
      0 -> 140.dp
      1 -> 200.dp
      else -> 160.dp
    }
  }

  Column(Modifier.fillMaxSize().background(Color.Transparent)) {
    NavigationBar("图书列表", if (isWaterFlow) Res.drawable.icon_list else Res.drawable.icon_picture, onLiftClick = { isWaterFlow = !isWaterFlow }, onRightClick = { goToManagerPage(null) })

    if (isWaterFlow) {
      WaterFlow(items = bookListState.response, estimateHeight = { item, _ -> waterfallImageHeight(item.id).value + 80f },) { book, _ ->
        WaterFlowCard(book.bookname, book.author, book.publisher, image = randomBookImage(), imageHeight = waterfallImageHeight(book.id), onClick = { goToManagerPage(book.id.toString()) })
      }
    } else {
      LazyColumn {
        items(bookListState.response, key = { it.id }) { book ->
          SwipeToDelete(onDelete = {
            currentDeletingId = book.id.toString()
            showConfirmDialog = true
          }) {
            BookCard(book.bookname, book.author, book.publisher, onClick = { goToManagerPage(book.id.toString()) })
          }
        }
      }
    }
  }

  // 删除完成后自动关闭弹窗
  LaunchedEffect(bookListState.isDeleting) {
    if (!bookListState.isDeleting && currentDeletingId.isNotEmpty()) {
      currentDeletingId = ""
      showConfirmDialog = false
    }
  }

  when(showConfirmDialog) {
    true -> {
      AlertDialog(
        {
          if (!bookListState.isDeleting) {
            currentDeletingId = ""
            showConfirmDialog = false
          }
        }, {
          TextButton({
            vm.deleteCurrentBookDatumAndReloadList(currentDeletingId, "wangbadan")
          }) {
            Text("确定")
          }
        }, dismissButton = {
          TextButton({
            if (!bookListState.isDeleting) {
              currentDeletingId = ""
              showConfirmDialog = false
            }
          }) {
            Text("取消")
          }
        }, title = {
          Text(if (bookListState.isDeleting) "正在删除" else "删除图书")
        }, text = {
          Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("确定删除该图书吗？删除后无法恢复。")
          }
        })
    }
    false -> {}
  }
}