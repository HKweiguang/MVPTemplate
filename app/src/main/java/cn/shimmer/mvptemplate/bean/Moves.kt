package cn.shimmer.mvptemplate.bean

data class Moves(
    val trailers: MutableList<Trailers>
)

data class Trailers(
    val id: Long,
    val movieName: String,
    val coverImg: String,
    val movieId: Long,
    val url: String,
    val hightUrl: String,
    val videoTitle: String,
    val videoLength: Int,
    val rating: Double,
    val type: MutableList<String>,
    val summary: String
)