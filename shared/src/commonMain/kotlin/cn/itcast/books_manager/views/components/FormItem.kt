package cn.itcast.books_manager.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FormItem(label: String, value: String, onValueChange: (String) -> Unit, placeholder: String = "") {
  Column(Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
    Text(label, Modifier.padding(bottom = 8.dp), fontSize = 14.sp, color = Color(0xFF333333))
    Row(Modifier.fillMaxWidth().height(48.dp).clip(RoundedCornerShape(24.dp)).background(Color(0xFFF2F2F7)), verticalAlignment = Alignment.CenterVertically,) {
      BasicTextField(value, onValueChange, Modifier.weight(1f).padding(horizontal = 16.dp), textStyle = TextStyle(fontSize = 15.sp, color = Color(0xFF23233C)),
        cursorBrush = SolidColor(Color(0xFF7F3DFF)),
        singleLine = true,
        decorationBox = { innerTextField ->
          Box(Modifier.height(48.dp), contentAlignment = Alignment.CenterStart) {
            if (value.isEmpty()) {
              Text(placeholder, fontSize = 15.sp, color = Color(0xFFD9D9D9))
            }
            innerTextField()
          }
        },
      )
      if (value.isNotEmpty()) {
        Box(Modifier.padding(end = 12.dp).height(20.dp).clip(RoundedCornerShape(10.dp)).background(Color(0xFFD0D0D8)).clickable { onValueChange("") }, contentAlignment = Alignment.Center,) {
          Text("\u00D7", Modifier.padding(horizontal = 4.dp), fontSize = 14.sp, color = Color.White)
        }
      }
    }
  }
}
