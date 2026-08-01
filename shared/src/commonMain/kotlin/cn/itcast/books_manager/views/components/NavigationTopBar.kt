package cn.itcast.books_manager.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.visible
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import booksmanager.shared.generated.resources.Res
import booksmanager.shared.generated.resources.icon_add
import booksmanager.shared.generated.resources.icon_folder
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable fun NavigationTopBar(title: String? = "默认标题", onLeftClick: (() -> Unit)? = null, onTitleClick: (() -> Unit)? = null, onRightClick: (() -> Unit)? = null, leftIcon: DrawableResource = Res.drawable.icon_folder, rightIcon: DrawableResource = Res.drawable.icon_add, showLeftIcon: Boolean = true, showRightIcon: Boolean = true) {
  Row(Modifier.fillMaxWidth().height(50.dp).background(Color.Transparent).padding(15.dp, 0.dp).safeContentPadding(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
    Image(painterResource(leftIcon), null, Modifier.size(24.dp).visible(showLeftIcon).clickable { onLeftClick?.invoke() })
    title?.let { Text(it, Modifier.clickable { onTitleClick?.invoke() }, fontSize = 20.sp, fontWeight = FontWeight.SemiBold) }
    Image(painterResource(rightIcon), null, Modifier.size(24.dp).visible(showRightIcon).clickable { onRightClick?.invoke() })
  }
}