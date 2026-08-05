package cn.itcast.books_manager.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

/**
 * 瀑布流卡片
 */
@Composable fun WaterFlowCard(bookname: String, author: String, publisher: String, image: DrawableResource, imageHeight: Dp = 160.dp, onClick: () -> Unit = {}, ) {
  Column(Modifier.fillMaxWidth().clickable { onClick() }) {
    Image(painterResource(image), null, Modifier.fillMaxWidth().height(imageHeight).clip(RoundedCornerShape(12.dp)), contentScale = ContentScale.Crop,)
    Text(bookname, Modifier.padding(top = 8.dp), fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF23233C), maxLines = 2, overflow = TextOverflow.Ellipsis,)
    Text(author, Modifier.padding(top = 4.dp), fontSize = 12.sp, color = Color(0xFF9E9EB8), maxLines = 1, overflow = TextOverflow.Ellipsis,)
    Text(publisher, Modifier.padding(top = 4.dp), fontSize = 12.sp, color = Color(0xFF9E9EB8), maxLines = 1, overflow = TextOverflow.Ellipsis,)
  }
}