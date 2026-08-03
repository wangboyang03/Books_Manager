package cn.itcast.books_manager.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.itcast.books_manager.ToastMessage
import cn.itcast.books_manager.models.BookItemResponse
import cn.itcast.books_manager.models.apis.BookManagerApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UIState(
  val loading: Boolean = false,
  var dataList: List<BookItemResponse> = emptyList(),
  val errorMessage: String = ""
)

class BookViewModel: ViewModel() {
  private val _uiState = MutableStateFlow(UIState())
  val uiState = _uiState.asStateFlow()

  init {
    ToastMessage.openToast("初始化ViewModel完成")
    viewModelScope.launch {
      _uiState.update {
        val response = BookManagerApi.getBookListApi(mapOf("creator" to "涛哥"))
        it.copy(dataList = response)
      }
    }
  }
}