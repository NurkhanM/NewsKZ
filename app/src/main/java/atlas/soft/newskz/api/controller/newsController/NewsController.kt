package atlas.soft.newskz.api.controller.newsController

import atlas.soft.newskz.models.news.index.NewsIndexModels
import atlas.soft.newskz.models.news.show.ShowNewsModels
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path

interface NewsController {

    @GET("api/news")
    suspend fun allNews(
    ): Response<NewsIndexModels>

    @GET("api/news/{idNews}")
    suspend fun infoNews(
        @Path("idNews") idNews: String
    ): Response<ShowNewsModels>
}