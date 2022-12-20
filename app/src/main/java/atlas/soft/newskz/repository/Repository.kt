package atlas.soft.newskz.repository

import atlas.soft.newskz.api.RetroFitInstance
import atlas.soft.newskz.models.auth.login.LoginModels
import atlas.soft.newskz.models.auth.register.RegisterModels
import atlas.soft.newskz.models.news.index.NewsIndexModels
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

class Repository {

    suspend fun allNewsRepository(): Response<NewsIndexModels> {
        return RetroFitInstance.newsController.allNews()
    }

    suspend fun postLoginRepository(params: JsonObject ): Response<LoginModels> {
        return RetroFitInstance.authController.postLogin(params)
    }

    suspend fun postRegisterAccRepository(params: HashMap<String, RequestBody>,  img: MultipartBody.Part?): Response<RegisterModels> {
        return RetroFitInstance.authController.postRegisterAcc( params, img)
    }

}