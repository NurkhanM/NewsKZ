package atlas.soft.newskz.api.controller.newsController

import atlas.soft.newskz.models.news.index.NewsIndexModels
import retrofit2.Response
import retrofit2.http.GET

interface NewsController {

    @GET("api/news")
    suspend fun allNews(
    ): Response<NewsIndexModels>
}