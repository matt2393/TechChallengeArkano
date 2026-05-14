package dev.mcallisaya.techchallenge.domain.use_case

import dev.mcallisaya.techchallenge.domain.model.RickMorty
import kotlinx.coroutines.flow.Flow

interface GetRickMortyUseCase {
    suspend operator fun invoke(page: Int): Flow<RickMorty>
}