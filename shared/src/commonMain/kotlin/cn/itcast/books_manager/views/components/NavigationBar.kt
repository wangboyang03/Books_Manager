package cn.itcast.books_manager.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.visible
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import booksmanager.shared.generated.resources.Res
import booksmanager.shared.generated.resources.icon_addnew
import booksmanager.shared.generated.resources.icon_list
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


@Composable fun NavigationBar(title: String? = "", leftIcon: DrawableResource = Res.drawable.icon_list, rightIcon: DrawableResource = Res.drawable.icon_addnew, showRightIcon: Boolean = true, showBorder: Boolean = true, onLiftClick: () -> Unit = {}, onRightClick: () -> Unit = {}) {
  Row(Modifier.fillMaxWidth().height(56.dp).padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
    // 左侧按钮
    Image(painterResource(leftIcon), null, Modifier.clickable { onLiftClick() }.size(24.dp))
    // 中间标题
    Text(title ?: "", Modifier.weight(1f).padding(horizontal = 8.dp), fontSize = 20.sp, textAlign = TextAlign.Center, maxLines = 1, overflow = TextOverflow.Ellipsis)
    // 右侧按钮
    Image(painterResource(rightIcon), null, Modifier.clickable { onRightClick() }.size(24.dp).visible(showRightIcon))
  }

  // 底部分割线
  if (showBorder) {
    HorizontalDivider(color = Color.Gray, thickness = 0.5.dp)
  }
}