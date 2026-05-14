package dev.mcallisaya.techchallenge.domain.use_case

import dev.mcallisaya.techchallenge.data.repository.RickMortyRepository
import dev.mcallisaya.techchallenge.domain.model.RickMorty
import dev.mcallisaya.techchallenge.domain.model.toErrorCode
import dev.mcallisaya.techchallenge.domain.model.toRickMorty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetRickMortyUseCaseImpl @Inject constructor(
    private val repository: RickMortyRepository
): GetRickMortyUseCase {
    override suspend fun invoke(page: Int): Flow<RickMorty> = flow {
        val res = repository.getCharacters(page)
        if (res.code() in 200..299 && res.body() != null) {
            emit(res.body()!!.toRickMorty())
        } else {
            emit(RickMorty(codeError = res.code().toErrorCode()))
        }
    }.flowOn(Dispatchers.IO)
}