package atlas.soft.newskz.models.news.show

data class Data(
    val body: String,
    val categories: List<Category>,
    val created_at: String,
    val description: String,
    val id: Int,
    val img: String,
    val name: String,
    val publication_date: String,
    val slug: String,
    val status: Int,
    val user: User,
    val views: Int
)