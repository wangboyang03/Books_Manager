package cn.itcast.books_manager.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun SwipeToDelete(onDelete: () -> Unit, content: @Composable () -> Unit) {
  val density = LocalDensity.current
  val deleteWidthPx = with(density) { 80.dp.toPx() }
  val thresholdPx = with(density) { 40.dp.toPx() }

  var offset by remember { mutableFloatStateOf(0f) }
  var isAnimating by remember { mutableStateOf(false) }
  var contentHeightPx by remember { mutableIntStateOf(0) }

  LaunchedEffect(isAnimating) {
    if (!isAnimating) return@LaunchedEffect
    val target = if (offset < -thresholdPx) -deleteWidthPx else 0f
    val steps = 12
    val stepValue = (target - offset) / steps
    repeat(steps) {
      if (!isAnimating) return@LaunchedEffect
      offset += stepValue
      delay(6)
    }
    offset = target
    isAnimating = false
  }

  Box(Modifier.fillMaxWidth(), Alignment.Center) {
    Box(Modifier.align(Alignment.CenterEnd).height(with(density) { contentHeightPx.toDp() }).width(80.dp).background(Color(0xFFFF3B30))
      .clickable {
        if (offset <= -deleteWidthPx + 1f) onDelete()
      }, Alignment.Center) {
      Text("删除", color = Color.White, fontSize = 15.sp)
    }

    Box(Modifier.fillMaxWidth().offset { IntOffset(offset.roundToInt(), 0) }.background(MaterialTheme.colorScheme.primaryContainer)
      .onSizeChanged { contentHeightPx = it.height }
      .pointerInput(Unit) {
        detectHorizontalDragGestures(
          onDragStart = { isAnimating = false },
          onDragEnd = { isAnimating = true },
          onDragCancel = { isAnimating = true },
        ) { _, dragAmount ->
          if (isAnimating) return@detectHorizontalDragGestures
          offset = (offset + dragAmount).coerceIn(-deleteWidthPx, 0f)
        }
      }
    ) {
      content()
    }
  }
}
