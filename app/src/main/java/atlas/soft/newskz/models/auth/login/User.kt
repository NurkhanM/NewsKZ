package atlas.soft.newskz.models.auth.login

data class User(
    val created_at: Any,
    val email: String,
    val email_verified_at: Any,
    val id: Int,
    val img: String,
    val name: String,
    val phone: String,
    val status: String
)