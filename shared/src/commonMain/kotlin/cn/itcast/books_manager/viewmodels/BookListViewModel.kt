package cn.itcast.books_manager.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.itcast.books_manager.ToastMessage
import cn.itcast.books_manager.apis.BookManagerApi
import cn.itcast.books_manager.models.BookDatumResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class BookListState(
  val isLoading: Boolean = false, // 是否正在加载中
  val response: List<BookDatumResponse> = emptyList(),
  val isDeleting: Boolean = false // 是否正在删除中
)

class BookListViewModel: ViewModel() {
  private val _bookListState = MutableStateFlow(BookListState())
  val bookListState = _bookListState.asStateFlow()

  /**
   * 获取图书数据列表
   */
  fun getBookDatumList(creator: String) {
    viewModelScope.launch {
      _bookListState.update {
        it.copy(isLoading = true)
      }
      try {
        val response = BookManagerApi.getBookDatumListApi(mapOf("creator" to creator))
        _bookListState.update {
          it.copy(isLoading = false, response = response)
        }
      } catch (error: Exception) {
        error.message?.let { ToastMessage(it) }
      } finally {
        _bookListState.update {
          it.copy(false)
        }
      }
    }
  }

  /**
   * 删除当前图书
   */
  fun deleteCurrentBookDatumAndReloadList(id: String, currentCreator: String){
    viewModelScope.launch {
      if (id.isBlank()) return@launch
      if (_bookListState.value.isDeleting) return@launch
      _bookListState.update {
        it.copy(isDeleting = true)
      }
      try {
        // 删除当前图书
        val response = BookManagerApi.deleteCurrentBookApi(id)
        getBookDatumList(currentCreator)
        ToastMessage("删除成功")
      } catch (error: Exception) {
        error.message?.let { ToastMessage(it) }
      } finally {
        _bookListState.update {
          it.copy(isDeleting = false)
        }
      }
    }
  }
}