package atlas.soft.newskz.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import atlas.soft.newskz.models.news.index.NewsIndexModels
import atlas.soft.newskz.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModels : ViewModel() {

    private val repo = Repository()

    val myAllNews: MutableLiveData<Response<NewsIndexModels>> = MutableLiveData()


    fun allNews() {
        viewModelScope.launch {
            myAllNews.value = repo.allNewsRepository()
        }
    }
}