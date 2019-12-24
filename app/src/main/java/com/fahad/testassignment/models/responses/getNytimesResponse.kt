package com.fahad.testassignment.models.responses

data class GetNytimesResponse(
    val fault: List<fault>,
    val message: String,
    val success: String,
    val status: String,
    val copyright: String,
    val num_results: Int,
    val results: List<results>

)

data class results(
    val url: String,
    val title: String,
    val abstract: String,
    val published_date: String,
    val byline: String,
    val media: List<media>
)

data class media(
    val faultstring: String,
    val `media-metadata` : List<media_metadata>
)

data class media_metadata(
    val url: String,
    val format: String,
    val height: String,
    val width: String
)

data class fault(
    val faultstring: Int,
    val detail: List<detail>
)


data class detail(
    val errorcode: Int
)