package cn.itcast.books_manager.core.network

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
  val message: String,
  val data: T?
)