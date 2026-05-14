package dev.mcallisaya.techchallenge.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dev.mcallisaya.techchallenge.util.baseUrl
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class ApiRest @Inject constructor() {
    private fun createBuilder(): Retrofit.Builder {
        val kotlinSerialization = Json {
            isLenient = true
            ignoreUnknownKeys = true
            coerceInputValues = true
            encodeDefaults = true
        }

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(kotlinSerialization.asConverterFactory("application/json".toMediaType()))
            .baseUrl(baseUrl)
    }

    fun <T> getService(cls: Class<out T>): T = createBuilder().build().create()
}