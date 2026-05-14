package dev.mcallisaya.techchallenge.domain.model

import dev.mcallisaya.techchallenge.data.model.RickMortyCharacterResponse

data class RickMorty(
    val codeError: ErrorCode? = null,
    val pages: Int = 0,
    val results: List<Result>? = null
) {
    data class Result(
        val created: String? = null,
        val episode: List<String?>? = null,
        val gender: String? = null,
        val id: Int? = null,
        val image: String? = null,
        val location: Location? = null,
        val name: String? = null,
        val origin: Origin? = null,
        val species: String? = null,
        val status: String? = null,
        val type: String? = null,
        val url: String? = null
    ) {

        data class Origin(
            val name: String? = null,
            val url: String? = null
        )


        data class Location(
            val name: String? = null,
            val url: String? = null
        )
    }
}

fun RickMortyCharacterResponse.toRickMorty() =
    RickMorty(
        pages = info?.pages ?: 0,
        results = results?.map { it.toResult() }
    )

private fun RickMortyCharacterResponse.Result.toResult() =
    RickMorty.Result(
        created = created,
        episode = episode,
        gender = gender,
        id = id,
        image = image,
        location = location?.toLocation(),
        name = name,
        origin = origin?.toOrigin(),
        species = species,
        status = status,
        type = type,
        url = url
    )

private fun RickMortyCharacterResponse.Result.Location.toLocation() =
    RickMorty.Result.Location(
        name = name,
        url = url
    )

private fun RickMortyCharacterResponse.Result.Origin.toOrigin() =
    RickMorty.Result.Origin(
        name = name,
        url = url
    )

enum class ErrorCode {
    NOT_FOUND,
    SERVER_ERROR,
    BAD_REQUEST,
    UNAUTHORIZED,
    FORBIDDEN,
    UNKNOWN
}

fun Int.toErrorCode(): ErrorCode {
    return when(this) {
        404 -> ErrorCode.NOT_FOUND
        500 -> ErrorCode.SERVER_ERROR
        400 -> ErrorCode.BAD_REQUEST
        401 -> ErrorCode.UNAUTHORIZED
        403 -> ErrorCode.FORBIDDEN
        else -> ErrorCode.UNKNOWN
    }
}