package com.dnar.dicodingsubmissionbfaa.data.network.setting

import okhttp3.Interceptor
import okhttp3.Response

// Interceptor Api; Keyword : Retrofit2
class BasicInterceptor(var token: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()
        val basicReq = req.newBuilder()
            .addHeader("Authorization", "token $token")
            .build()

        return chain.proceed(basicReq)
    }
}