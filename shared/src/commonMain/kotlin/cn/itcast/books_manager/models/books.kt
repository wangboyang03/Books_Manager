package cn.itcast.books_manager.models

import kotlinx.serialization.Serializable

@Serializable
data class BookItemResponse(
  val id: Int,
  val bookname: String,
  val author: String,
  val publisher: String
)