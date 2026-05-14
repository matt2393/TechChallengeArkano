package dev.mcallisaya.techchallenge.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RickMortyCharacterResponse(
    @SerialName("info")
    val info: Info? = null,
    @SerialName("results")
    val results: List<Result>? = null
) {

    @Serializable
    data class Result(
        @SerialName("created")
        val created: String? = null,
        @SerialName("episode")
        val episode: List<String?>? = null,
        @SerialName("gender")
        val gender: String? = null,
        @SerialName("id")
        val id: Int? = null,
        @SerialName("image")
        val image: String? = null,
        @SerialName("location")
        val location: Location? = null,
        @SerialName("name")
        val name: String? = null,
        @SerialName("origin")
        val origin: Origin? = null,
        @SerialName("species")
        val species: String? = null,
        @SerialName("status")
        val status: String? = null,
        @SerialName("type")
        val type: String? = null,
        @SerialName("url")
        val url: String? = null
    ) {

        @Serializable
        data class Origin(
            @SerialName("name")
            val name: String? = null,
            @SerialName("url")
            val url: String? = null
        )


        @Serializable
        data class Location(
            @SerialName("name")
            val name: String? = null,
            @SerialName("url")
            val url: String? = null
        )
    }


    @Serializable
    data class Info(
        @SerialName("count")
        val count: Int? = null,
        @SerialName("next")
        val next: String? = null,
        @SerialName("pages")
        val pages: Int? = null,
        @SerialName("prev")
        val prev: String? = null
    )
}