package cn.itcast.books_manager.views.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * 封装了一个类似鸿蒙 WaterFlow的瀑布流组件
 */
@Composable fun <T> WaterFlow(items: List<T>, modifier: Modifier = Modifier, columns: Int = 2, spacing: Dp = 8.dp, estimateHeight: ((item: T, index: Int) -> Float)? = null, content: @Composable (item: T, index: Int) -> Unit) {
  // 计算出新元素贪心放进当前最矮的一列 天然形成错落
  val distributed = remember(items) {
    val buckets = Array(columns) { mutableListOf<Pair<T, Int>>() }
    val heights = FloatArray(columns)
    items.forEachIndexed { index, item ->
      var target = 0
      if (estimateHeight != null) {
        for (i in 1 until columns) if (heights[i] < heights[target]) target = i
        heights[target] += estimateHeight(item, index)
      } else {
        target = index % columns
      }
      buckets[target].add(item to index)
    }
    buckets
  }

  Row(modifier = modifier.fillMaxWidth().verticalScroll(rememberScrollState()).padding(horizontal = spacing), horizontalArrangement = Arrangement.spacedBy(spacing)) {
    distributed.forEach { bucket ->
      Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(spacing)) {
        bucket.forEach {
          (item, index) -> content(item, index)
        }
      }
    }
  }
}