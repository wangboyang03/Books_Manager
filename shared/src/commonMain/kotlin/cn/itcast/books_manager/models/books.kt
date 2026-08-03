package cn.itcast.books_manager.models

import kotlinx.serialization.Serializable

@Serializable data class BookItemResponse(
  val id: Int,
  val bookname: String,
  val author: String,
  val publisher: String
)

@Serializable data class BookRequest (
  /**
   * 新增图书作者
   */
  val author: String,

  /**
   * 新增图书名字
   */
  val bookname: String,

  /**
   * 新增图书创建者，自己的外号，和获取图书时的外号相同
   */
  val creator: String,

  /**
   * 新增图书出版社
   */
  val publisher: String
)