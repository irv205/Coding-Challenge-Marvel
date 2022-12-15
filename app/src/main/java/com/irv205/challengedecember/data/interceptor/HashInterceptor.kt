package com.irv205.challengedecember.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import com.irv205.challengedecember.core.utils.md5
import com.irv205.challengedecember.BuildConfig.*

class HashInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {


        val currentTimestamp = System.currentTimeMillis()
        val hash: String = md5(currentTimestamp.toString() + MY_PRIVATE_KEY + MY_PUBLIC_KEY)

        val original = chain.request()
        val originalHttpUrl = original.url

        val url = originalHttpUrl.newBuilder().addQueryParameter("ts", currentTimestamp.toString())
            .addQueryParameter(
                "apikey",
                MY_PUBLIC_KEY
            ).addQueryParameter("hash", hash).build()

        val requestBuilder = original.newBuilder()
            .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}