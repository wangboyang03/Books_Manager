package cn.itcast.books_manager.utils

import cn.itcast.books_manager.models.Constants
import cn.itcast.books_manager.models.ResponseData
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull

/**
 * 请求中间层 用于将HttpClient底层与接口封装链接的桥梁 暴露相关实现好的Http方法
 */
class AppClient(val client: HttpClient) {
  /**
   * @param url 请求地址
   * @param params Query/path
   */
  suspend inline fun<reified T> get(url: String, params: Map<String, Any>? = emptyMap()): T {
    val response: HttpResponse = client.get(url) {
      // 遍历集合中每一个参数key-value取出
      params?.forEach {
        (key, value) -> parameter(key, value)
      }
    }
    return parseResponse<T>(response.bodyAsText())
  }

  suspend inline fun <reified T>post(url: String, data: Any? = null): T {
    val response = client.post(url) {
      contentType(ContentType.Application.Json) // 内容类型 application/json
      setBody(data)
    }
    return parseResponse<T>(response.bodyAsText())
  }

  suspend inline fun <reified T>put(url: String, data: Any? = null): T {
    val response = client.put(url) {
      contentType(ContentType.Application.Json)
      setBody(data)
    }
    return parseResponse<T>(response.bodyAsText())
  }

  suspend inline fun <reified T>delete(url: String, data: Any? = null): T {
    val response = client.delete(url) {
      contentType(ContentType.Application.Json)
      setBody(data)
    }
    return parseResponse<T>(response.bodyAsText())
  }

  /**
   * 响应反序列化工具函数
   */
  inline fun<reified T> parseResponse(string: String): T {
    // 序列化数据 接口返回统一携带公共请求体以及 每个data中的具体类型数据 再转成对象
    val response = HttpClientFactory.JSON.decodeFromString<ResponseData<JsonElement?>>(string)
    // 处理类型T 类型不为空
    when(T::class == Unit::class) {
      true -> return Unit as T
      else -> {
        val data = response.data
        if (data == null || data == JsonNull) {
          // 真正返回data也为空
          throw Exception("请求异常或data为空")
        }
        // 返回剥离后的数据
        return HttpClientFactory.JSON.decodeFromString<T>(data.toString())
      }
    }
  }
}

/**
 * 导出实例化出请求工具单例
 */
val networkClient = AppClient(HttpClientFactory.httpClient)

/**
 * 生产HttpClient的工厂函数
 */
object HttpClientFactory {
  // 配置JSON序列化工具
  val JSON = Json {
    ignoreUnknownKeys = true // 忽略接口中未知字段
  }

  // 创建HttpClient实例对象
  val httpClient = create()

  fun create(): HttpClient {
    return HttpClient {
      defaultRequest() {
        url(Constants.BASE_URL) // 配置基地址
      }
      install(HttpTimeout) {
        // 配置超时时间
        connectTimeoutMillis = Constants.TIME_OUT
        requestTimeoutMillis = Constants.TIME_OUT
      }
      install(ContentNegotiation) {
        json(JSON) // 请求体数据序列化
      }
    }
  }
}