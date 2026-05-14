package dev.mcallisaya.techchallenge.data.repository

import dev.mcallisaya.techchallenge.data.model.RickMortyCharacterResponse
import dev.mcallisaya.techchallenge.data.network.service.RickMortyService
import retrofit2.Response
import javax.inject.Inject

class RickMortyRepositoryImpl @Inject constructor(
    private val rickMortyService: RickMortyService
) : RickMortyRepository {
    override suspend fun getCharacters(page: Int): Response<RickMortyCharacterResponse> {
        return rickMortyService.getCharacters(page)
    }
}

