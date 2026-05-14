package dev.mcallisaya.techchallenge.data.repository

import dev.mcallisaya.techchallenge.data.model.RickMortyCharacterResponse
import dev.mcallisaya.techchallenge.data.network.ApiRest
import dev.mcallisaya.techchallenge.data.network.service.RickMortyService
import retrofit2.Response
import javax.inject.Inject

class RickMortyRepositoryImpl @Inject constructor(
    private val apiRest: ApiRest
): RickMortyRepository {
    override suspend fun getCharacters(page: Int): Response<RickMortyCharacterResponse> {
        return apiRest.getService(RickMortyService::class.java).getCharacters(page)
    }
}

