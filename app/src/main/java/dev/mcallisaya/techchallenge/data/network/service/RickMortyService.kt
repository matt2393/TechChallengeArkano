package dev.mcallisaya.techchallenge.data.network.service

import dev.mcallisaya.techchallenge.data.model.RickMortyCharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickMortyService {

    @GET("/character")
    suspend fun getCharacters(@Query("page") page: Int): Response<RickMortyCharacterResponse>
}