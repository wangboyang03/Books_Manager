package cn.itcast.books_manager.models.apis

import cn.itcast.books_manager.core.network.appClient
import cn.itcast.books_manager.models.BookItemResponse
import cn.itcast.books_manager.models.BookRequest
import cn.itcast.books_manager.models.Constants

object BookManagerApi {
  suspend fun getBookListApi(params: Map<String, Any>): List<BookItemResponse> {
    println("Api参数=$params")
    return appClient.get<List<BookItemResponse>>(Constants.GET_BOOK_LIST_API, params)
  }
  suspend fun addBookApi(data: BookRequest) {
    return appClient.post(Constants.GET_BOOK_LIST_API, data)
  }
  suspend fun updateBookApi(id: Int, data: BookRequest) {
    return appClient.put("${Constants.GET_BOOK_LIST_API}/$id", data)
  }
  suspend fun deleteBookApi(id: Int) {
    return appClient.delete("${ Constants.GET_BOOK_LIST_API }/$id")
  }
  suspend fun getBookDetailApi(id: Int): BookItemResponse {
    return appClient.get<BookItemResponse>("${Constants.GET_BOOK_LIST_API}/$id")
  }
}