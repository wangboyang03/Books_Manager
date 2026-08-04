package cn.itcast.books_manager.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.itcast.books_manager.ToastMessage
import cn.itcast.books_manager.apis.BookManagerApi
import cn.itcast.books_manager.models.BookDatumRequest
import cn.itcast.books_manager.models.BookDatumResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class BookManagerState(
  val isLoading: Boolean = false,
  val isSaving: Boolean = false,
  val currentBookDatum: BookDatumResponse? = null
)

sealed interface BookManagerEvent {
  data class SaveEvent(val isSuccess: Boolean = false): BookManagerEvent
}

class BookManagerViewModel: ViewModel() {
  private val _bookManagerState = MutableStateFlow(BookManagerState())
  val bookManagerState = _bookManagerState.asStateFlow()

  private val _event = MutableSharedFlow<BookManagerEvent>(extraBufferCapacity = 1)
  val event = _event.asSharedFlow()

  /**
   * 获取当前图书数据
   */
  fun getCurrentBookDatum(id: String) {
    viewModelScope.launch {
      _bookManagerState.update {
        it.copy(isLoading = true)
      }
      try {
        val response = BookManagerApi.getCurrentBookDatumApi(id)
        _bookManagerState.update {
          it.copy(isLoading = false, currentBookDatum = response)
        }
      } catch (error: Exception) {
        error.message?.let { ToastMessage(it) }
      } finally {
        _bookManagerState.update {
          it.copy(isLoading = false)
        }
      }
    }
  }

  /**
   * 保存当前图书数据
   */
  fun savedCurrentBookDatum(request: BookDatumRequest, id: String? = null) {
    viewModelScope.launch {
      _bookManagerState.update {
        it.copy(isSaving = true)
      }
      try {
        when(id) {
          null -> BookManagerApi.insertNewBookApi(request)
          else -> BookManagerApi.updateCurrentBookDatumApi(id, request)
        }
        _bookManagerState.update {
          it.copy(isSaving = false)
        }

        // 此时需要通知外部保存成功 外部自行操作
        _event.emit(BookManagerEvent.SaveEvent(isSuccess = true))
      } catch (error: Exception) {
        error.message?.let { ToastMessage(it) }
        // 此时需要通知外部保存失败 外部自行操作
        _event.emit(BookManagerEvent.SaveEvent(isSuccess = false))
      } finally {
        _bookManagerState.update {
          it.copy(isSaving = false)
        }
      }
    }
  }
}