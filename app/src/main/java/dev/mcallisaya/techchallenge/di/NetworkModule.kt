package dev.mcallisaya.techchallenge.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.mcallisaya.techchallenge.data.network.service.RickMortyService
import dev.mcallisaya.techchallenge.util.baseUrl
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val kotlinSerialization = Json {
            isLenient = true
            ignoreUnknownKeys = true
            coerceInputValues = true
            encodeDefaults = true
        }
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(kotlinSerialization.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun provideRickMortyService(retrofit: Retrofit): RickMortyService {
        return retrofit.create(RickMortyService::class.java)
    }
}