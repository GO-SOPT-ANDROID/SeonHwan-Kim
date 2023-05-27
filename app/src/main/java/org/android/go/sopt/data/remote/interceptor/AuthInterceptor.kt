package org.android.go.sopt.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
        val newRequest = originRequest.newBuilder()
            .header(
                "Authorization",
                "hiihihiihhihiihihihi",
            ).build()
        return chain.proceed(newRequest)
    }
}
