package atlas.soft.newskz.api

import atlas.soft.newskz.api.controller.authController.AuthController
import atlas.soft.newskz.api.controller.filterController.FilterController
import atlas.soft.newskz.api.controller.newsController.NewsController
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


object RetroFitInstance {
    private val retrofit by lazy {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES) // write timeout
            .readTimeout(2, TimeUnit.MINUTES) // read timeout
            .addInterceptor(interceptor)
            .build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder().baseUrl("https://atlassoft.space/news/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }
    val authController: AuthController by lazy {
        retrofit.create(AuthController::class.java)
    }
    val newsController: NewsController by lazy {
        retrofit.create(NewsController::class.java)
    }
    val filterController: FilterController by lazy {
        retrofit.create(FilterController::class.java)
    }

}