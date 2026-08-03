package cn.itcast.books_manager.core.network

import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull

class AppClient(@PublishedApi internal val client: HttpClient) {
  suspend inline fun <reified T> get(url: String, params: Map<String, Any>? = emptyMap()): T {
    val response = client.get(url) {
      params?.forEach { (key, value) ->
        parameter(key, value)
      }
    }
    /*val response = client.get(
      "api/books?creator=6666"
    )*/
    println("url=$url")
    println("status=${response.status}")
    println("body=$response.bodyAsText()")
    return parseResponse<T>(response.bodyAsText())
  }

  suspend inline fun <reified T> post(url: String, data: Any?): T {
    val response = client.post(url) {
      contentType(ContentType.Application.Json)
      setBody(data)
    }
    return parseResponse<T>(response.bodyAsText())
  }

  suspend inline fun <reified T> put(url: String, data: Any?): T {
    val response = client.put(url) {
      contentType(ContentType.Application.Json)
      setBody(data)
    }
    return parseResponse<T>(response.bodyAsText())
  }

  suspend inline fun <reified T> delete(url: String, data: Any? = null): T {
    val response = client.delete(url) {
      contentType(ContentType.Application.Json)
      setBody(data)
    }
    return parseResponse<T>(response.bodyAsText())
  }

  /**
   * 封装序列化方法
   */
  inline fun<reified T> parseResponse(jsonString: String): T {
    val response = HttpClientFactory.JSON.decodeFromString<ApiResponse<JsonElement>>(jsonString)
    // 如果当前传入的类型是空类型 直接返回空类型 因为T有可能是具体的List<BookItem> 也有可能是一个Unit
    if (T::class == Unit::class) {
      return Unit as T
    }
    val data = response.data // 此时返回类型是具体的类型
    if (data == null || data == JsonNull) {
      throw Exception("响应异常")
    }
    return HttpClientFactory.JSON.decodeFromString<T>(data.toString())
  }
}

/**
 * 实例化出全局单例请求工具
 */
val appClient = AppClient(HttpClientFactory.httpClient)