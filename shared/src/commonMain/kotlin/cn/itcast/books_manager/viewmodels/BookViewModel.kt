package cn.itcast.books_manager.viewmodels

import androidx.lifecycle.ViewModel
import cn.itcast.books_manager.ToastMessage

class BookViewModel: ViewModel() {
  init {
    ToastMessage.openToast("初始化ViewModel完成")
  }
}