import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp

actual fun createCommonHttpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient {
    return HttpClient(OkHttp) {
        commonHttpClientConfig(config)
    }
}