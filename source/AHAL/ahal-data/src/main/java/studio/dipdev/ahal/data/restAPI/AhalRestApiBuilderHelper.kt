package studio.dipdev.ahal.data.restAPI

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

public open class AhalRestApiBuilderHelper {
    private var retrofit: Retrofit? = null
    private val interceptors: MutableList<Interceptor> = ArrayList()
    private val gsonTypeAdapters: MutableMap<Class<*>, Any> = HashMap()

    init {
        //addGsonTypeAdapter(OfferResponse.OfferType::class.java, OfferTypeParser())
    }

    private fun addInterceptor(interceptor: Interceptor): AhalRestApiBuilderHelper {
        interceptors.add(interceptor)
        return this
    }

    fun addLoggingInterceptor(level: HttpLoggingInterceptor.Level): AhalRestApiBuilderHelper {
        addInterceptor(HttpLoggingInterceptor().setLevel(level))
        return this
    }

    fun <T> createRetrofitApi(url: String, service: Class<T>): T {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(url)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(provideGsonConverterFactory())
                    .client(createOkHttpClient())
                    .build()
        }

        return retrofit!!.create(service)
    }

    private fun createOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)

        for (interceptor in interceptors) {
            builder.addInterceptor(interceptor)
        }

        return builder.build()
    }

    private fun provideGsonConverterFactory(): GsonConverterFactory {
        val gsonBuilder = GsonBuilder()

        for ((key, value) in gsonTypeAdapters) {
            gsonBuilder.registerTypeAdapter(key, value)
        }

        return GsonConverterFactory.create(gsonBuilder.create())
    }

    fun addGsonTypeAdapter(type: Class<*>, typeAdapter: Any): AhalRestApiBuilderHelper {
        gsonTypeAdapters[type] = typeAdapter
        return this
    }
}
