package core.api

import com.google.gson.GsonBuilder
import com.sh.ex.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService {

    companion object {

        private const val base_url: String = "https://px-socket-emitter.herokuapp.com/"

        private val interceptor: Interceptor
            get() = HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY
            }

        private val client: OkHttpClient
            get() = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

        private val gson = GsonBuilder()
                .setLenient()
                .create()

        fun initEndPoint(): Retrofit {
            return Retrofit.Builder().apply {
                baseUrl(base_url)
                client(client)
                addConverterFactory(GsonConverterFactory.create(gson))
            }.build()
        }

        fun initEndPoint(url: String): Retrofit {
            return Retrofit.Builder().apply {
                baseUrl(url)
                client(client)
                addConverterFactory(GsonConverterFactory.create(gson))
            }.build()
        }
    }

}