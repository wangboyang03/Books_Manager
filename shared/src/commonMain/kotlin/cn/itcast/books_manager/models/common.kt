package cn.itcast.books_manager.models

import kotlinx.serialization.Serializable

@Serializable data class ResponseData<T>(
  /**
   * 响应数组
   */
  val data: T?,

  /**
   * 响应消息
   */
  val message: String
)
