package cn.itcast.books_manager.models

import kotlinx.serialization.Serializable

@Serializable data class BookDatumResponse(
  /**
   * 图书作者
   */
  val author: String,

  /**
   * 图书名字
   */
  val bookname: String,

  /**
   * 图书id
   */
  val id: String,

  /**
   * 图书出版社
   */
  val publisher: String
)
