package atlas.soft.newskz.api.controller.filterController

import atlas.soft.newskz.models.news.index.NewsIndexModels
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface FilterController {

    @GET("api/news")
    suspend fun getSortProducts(
        @Header("Authorization") auth: String,
        @QueryMap allPro: HashMap<String, String>
    ): Response<NewsIndexModels>


}