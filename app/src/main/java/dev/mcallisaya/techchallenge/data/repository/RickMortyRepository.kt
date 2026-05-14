package dev.mcallisaya.techchallenge.data.repository

import dev.mcallisaya.techchallenge.data.model.RickMortyCharacterResponse
import retrofit2.Response

interface RickMortyRepository {

    suspend fun getCharacters(page: Int): Response<RickMortyCharacterResponse>
}