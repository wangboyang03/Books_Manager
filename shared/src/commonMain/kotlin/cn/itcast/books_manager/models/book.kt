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
  val id: Int,

  /**
   * 图书出版社
   */
  val publisher: String
)

@Serializable data class BookDatumRequest (
  /**
   * 图书作者
   */
  val author: String,

  /**
   * 图书名字
   */
  val bookname: String,

  /**
   * 图书创建者，写上自己名字-管理自己的数据
   */
  val creator: String,

  /**
   * 图书出版社
   */
  val publisher: String
)

@Serializable data class BookDeleteResponse(
  val id: String
)