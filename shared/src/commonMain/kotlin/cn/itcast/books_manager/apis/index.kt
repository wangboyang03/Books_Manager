package cn.itcast.books_manager.apis

import cn.itcast.books_manager.models.BookDatumRequest
import cn.itcast.books_manager.models.BookDatumResponse
import cn.itcast.books_manager.models.Constants
import cn.itcast.books_manager.utils.networkClient

object BookManagerApi {
  /**
   * 获取图书数据列表
   */
  suspend fun getBookDatumListApi(params: Map<String, Any>): List<BookDatumResponse> {
    return networkClient.get<List<BookDatumResponse>>(Constants.BOOK_DATUM_API, params)
  }

  /**
   * 获取当前图书详情
   */
  suspend fun getCurrentBookDatumApi(id: String): BookDatumResponse {
    return networkClient.get("${Constants.BOOK_DATUM_API}/$id")
  }

  /**
   * 新增图书
   */
  suspend fun insertNewBookApi(data: BookDatumRequest) {
    return networkClient.post(Constants.BOOK_DATUM_API, data)
  }

  /**
   * 修改当前图书
   */
  suspend fun updateCurrentBookDatumApi(id: String, data: BookDatumRequest) {
    return networkClient.put("${Constants.BOOK_DATUM_API}/$id", data)
  }

  /**
   * 删除当前图书
   */
  suspend fun deleteCurrentBookApi(id: String) {
    return networkClient.delete("${Constants.BOOK_DATUM_API}/$id")
  }
}