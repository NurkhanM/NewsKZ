package atlas.soft.newskz.models.auth.register.registerErrors

data class Errors(
    val email: List<String>,
    val img: List<String>,
    val name: List<String>,
    val password: List<String>,
    val phone: List<String>
)