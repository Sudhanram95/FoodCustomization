package com.sudhan.synupproject.network

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.sudhan.synupproject.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkUtil {

    var retrofit: Retrofit? = null
    var okHttpClient: OkHttpClient? = null
    val TIMEOUT:Long = 60

    fun retrofitHelper(context: Context):Retrofit? {
        if (okHttpClient == null) {
            val httpClient = OkHttpClient().newBuilder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)

            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            httpClient.addInterceptor(interceptor)

            okHttpClient = httpClient.build()
        }

        if(retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}