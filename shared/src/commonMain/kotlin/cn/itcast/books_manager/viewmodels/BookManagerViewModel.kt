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

data class BookManagerUIState(
  val loading: Boolean = false,
  val detail: BookItemResponse? = null
)

class BookManagerViewModel: ViewModel() {
  private val _uiState = MutableStateFlow(BookManagerUIState())
  val uiState = _uiState.asStateFlow()

  fun getBookDetail(id: String) {
    viewModelScope.launch {
      try {
        _uiState.update {
          it.copy(loading = true)
        }
        val response = BookManagerApi.getBookDetailApi(id)
        _uiState.update {
          it.copy(loading = false, detail = response)
        }
      } catch (error: Exception) {
        error.message?.let { ToastMessage.openToast(it) }
      } finally {
        _uiState.update {
          it.copy(loading = false)
        }
      }
    }
  }
}