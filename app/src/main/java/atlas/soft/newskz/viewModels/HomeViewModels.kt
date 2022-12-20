package atlas.soft.newskz.viewModels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import atlas.soft.newskz.models.auth.login.LoginModels
import atlas.soft.newskz.models.auth.register.RegisterModels
import atlas.soft.newskz.models.news.index.NewsIndexModels
import atlas.soft.newskz.repository.Repository
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import kotlin.math.log

class HomeViewModels : ViewModel() {

    private val repo = Repository()

    val myAllNews: MutableLiveData<Response<NewsIndexModels>> = MutableLiveData()
    val myLogin: MutableLiveData<Response<LoginModels>> = MutableLiveData()
    val myRegisterAcc: MutableLiveData<Response<RegisterModels>> = MutableLiveData()


    fun allNews() {
        viewModelScope.launch {
            myAllNews.value = repo.allNewsRepository()
        }
    }

    fun postLogin(params: JsonObject ) {
        viewModelScope.launch {
            myLogin.value = repo.postLoginRepository(params)
        }
    }
    fun postRegisterAcc (params: HashMap<String, RequestBody>, img: MultipartBody.Part? ) {
        viewModelScope.launch {
            myRegisterAcc.value = repo.postRegisterAccRepository(params, img)
        }

        Log.d(TAG, "postRegisterAcc: $params")
    }

}