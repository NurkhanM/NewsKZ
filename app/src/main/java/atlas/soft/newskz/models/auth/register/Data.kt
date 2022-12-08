package atlas.soft.newskz.models.auth.register

data class Data(
    val created_at: String,
    val email: String,
    val email_verified_at: Any,
    val id: Int,
    val img: String,
    val name: String,
    val phone: String,
    val status: String
)