package project.dheeraj.cinewatch.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.*
import project.dheeraj.cinewatch.data.local.dao.BookmarkDao
import project.dheeraj.cinewatch.data.model.Movie
import project.dheeraj.cinewatch.data.model.MovieDB
import project.dheeraj.cinewatch.data.repository.NetworkRepository
import timber.log.Timber.e

@ExperimentalCoroutinesApi
class MovieDetailsViewModel @ViewModelInject constructor(
        private val databaseDao : BookmarkDao,
        private val networkRepository: NetworkRepository
) : ViewModel() {

    private val _name = MutableLiveData("Movie Name")
    private val _movie = MutableLiveData<Movie>()
    var movie : MutableLiveData<Movie> = _movie


    var bookmark = MutableLiveData(false)
    var movieName : MutableLiveData<String> = _name



    fun getMovieDetails(movie_id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val apiResponse = networkRepository.getMovieDetails(movie_id)
            movie.postValue(apiResponse)
        }
    }



    fun bookmarkMovie() {
        movie.value!!.apply {
            val movieDb = MovieDB(id, poster_path!!, overview!!, title!!, backdrop_path!!)
            viewModelScope.launch(Dispatchers.IO) {
                if (bookmark.value == true) {
                    databaseDao.removeMovie(movieDb)
                }
                else {
                    databaseDao.insertMovie(movieDb)
                }
            }
            e("Bookmark")
        }
    }

    fun checkBookmarkExist() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = databaseDao.bookmarkExist(movie.value!!.id)
            bookmark.postValue(response)
        }
    }

}