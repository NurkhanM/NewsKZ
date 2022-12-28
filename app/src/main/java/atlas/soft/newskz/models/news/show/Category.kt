package atlas.soft.newskz.models.news.show

data class Category(
    val created_at: String,
    val description: String,
    val id: Int,
    val img: String,
    val name: String,
    val parent_id: Any,
    val slug: String
)