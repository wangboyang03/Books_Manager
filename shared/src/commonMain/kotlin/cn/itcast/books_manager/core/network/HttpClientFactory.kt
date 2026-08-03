package cn.itcast.books_manager.core.network

import cn.itcast.books_manager.models.Constants
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import kotlinx.serialization.json.Json

object HttpClientFactory {
  private const val BASE_URL: String = Constants.BASE_URL
  private const val TIME_OUT: Long = 10000L

  /**
   * 创建JSON序列化实例 开启忽略字段 宽容解析
   */
  val JSON = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
    isLenient = true
  }

  /**
   * 创建一个httpClient实例
   * @return HttpClient
   */
  fun create(): HttpClient {
    return HttpClient {
      defaultRequest {
        url(BASE_URL)
      }
      install(HttpTimeout) {
        // 默认请求配置 配置基地址在defaultRequest中配置
        connectTimeoutMillis = TIME_OUT
        requestTimeoutMillis = TIME_OUT
      }
      install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
      }
    }
  }

  /**
   * 实例化出单例方法
   */
  val httpClient = create()
}