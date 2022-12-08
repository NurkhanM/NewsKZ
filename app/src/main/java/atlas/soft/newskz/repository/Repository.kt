package atlas.soft.newskz.repository

import atlas.soft.newskz.api.RetroFitInstance
import atlas.soft.newskz.models.news.index.NewsIndexModels
import retrofit2.Response

class Repository {

    suspend fun allNewsRepository(): Response<NewsIndexModels> {
        return RetroFitInstance.newsController.allNews()
    }

}