package cn.itcast.books_manager.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import booksmanager.shared.generated.resources.Res
import booksmanager.shared.generated.resources.images
import cn.itcast.books_manager.models.BookItemResponse
import cn.itcast.books_manager.views.components.NavigationTopBar
import org.jetbrains.compose.resources.painterResource

@Composable fun BookList(toBookEditor: (id: Int?) -> Unit = {}) {
  val bookList = listOf<BookItemResponse>(
    BookItemResponse(1, "三国演义", "罗贯中", "某出版社"),
    BookItemResponse(2, "西游记", "吴承恩", "某出版社"),
    BookItemResponse(3, "水浒传", "施耐庵", "某出版社"),
    BookItemResponse(4, "红楼梦", "曹雪芹", "某出版社")
  )

  Column(Modifier.fillMaxSize().safeContentPadding()) {
    NavigationTopBar("图书列表页", {}, {}, { toBookEditor(null) })
    LazyColumn() {
      items(bookList) {item ->
        BookCeil(item.bookname, item.author, item.publisher, { toBookEditor(item.id) })
      }
    }
  }
}

@Composable fun BookCeil(bookname: String, author: String, publisher: String, toBookEditor: () -> Unit = {}) {
  Row(Modifier.fillMaxSize().clickable(onClick = toBookEditor)) {
    Image(painterResource(Res.drawable.images), null, Modifier.height(150.dp).clip(RoundedCornerShape(20.dp)), contentScale = ContentScale.Crop)
    Column(Modifier.height(150.dp).weight(1f).padding(0.dp, 10.dp).padding(start = 16.dp), Arrangement.SpaceBetween) {
      Text(bookname, fontSize = 26.sp)
      Text(author, color = Color.Gray)
      Text(publisher, color = Color.Gray)
    }
  }
  Spacer(Modifier.height(10.dp))
}