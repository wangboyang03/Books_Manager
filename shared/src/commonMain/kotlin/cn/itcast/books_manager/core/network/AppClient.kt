package cn.itcast.books_manager.core.network

import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AppClient(@PublishedApi internal val client: HttpClient) {
  suspend fun <T> get(url: String, params: Map<String, Any>?): T {
    val response = client.get(url) {
      params?.forEach { (key, value) -> {
        parameter(key, value)
      } }
    }
    return null as T
  }

  suspend fun <T> post(url: String, data: Any?): T {
    val response = client.post(url) {
      contentType(ContentType.Application.Json)
      setBody(data)
    }
    return null as T
  }

  suspend fun <T> put(url: String, data: Any?): T {
    val response = client.put(url) {
      contentType(ContentType.Application.Json)
      setBody(data)
    }
    return null as T
  }

  suspend fun <T> delete(url: String, data: Any?): T {
    val response = client.delete(url) {
      contentType(ContentType.Application.Json)
      setBody(data)
    }
    return null as T
  }
}