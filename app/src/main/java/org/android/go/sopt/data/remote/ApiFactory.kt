package org.android.go.sopt.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.android.go.sopt.BuildConfig
import org.android.go.sopt.data.remote.service.HomeUserService
import org.android.go.sopt.data.remote.service.SignInService
import org.android.go.sopt.data.remote.service.SignUpService
import retrofit2.Retrofit

object ApiFactory {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    // inline에 대해 알아보기
    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object ReqresApiFactory {
    val json = Json { ignoreUnknownKeys = true }
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object ServicePool {
    val signUpService = ApiFactory.create<SignUpService>()
    val signInService = ApiFactory.create<SignInService>()
    val getHomeUserService = ReqresApiFactory.create<HomeUserService>()
}