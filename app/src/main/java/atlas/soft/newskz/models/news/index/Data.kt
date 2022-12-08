package atlas.soft.newskz.models.news.index

data class Data(
    val body: String,
    val created_at: String,
    val description: String,
    val id: Int,
    val img: String,
    val name: String,
    val publication_date: String,
    val slug: String,
    val status: Int,
    val views: Int
)