package atlas.soft.newskz.api.controller.authController

import atlas.soft.newskz.models.auth.login.LoginModels
import atlas.soft.newskz.models.auth.register.RegisterModels
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface AuthController {

    @Headers("Accept: application/json")
    @POST("api/auth/login")
    suspend fun postLogin(
        @Body body: JsonObject
    ): Response<LoginModels>



    @Multipart
    @Headers("Accept: application/json")
    @POST("/news/api/auth/register")
    suspend fun postRegisterAcc(
        @PartMap params: HashMap<String, RequestBody>,
//        @Part("name") name: RequestBody,
//        @Part("email") email: RequestBody,
//        @Part("password") password: RequestBody,
//        @Part("password_confirmation") password_confirmation: RequestBody,
//        @Part("country_id") country_id: RequestBody,
//        @Part("type") type: RequestBody,
//        @Part("phone") phone: RequestBody,
        @Part img: MultipartBody.Part?

        ): Response<RegisterModels>
}