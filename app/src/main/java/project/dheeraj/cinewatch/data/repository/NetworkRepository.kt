package project.dheeraj.cinewatch.data.repository

import project.dheeraj.cinewatch.data.api.NetworkService
import project.dheeraj.cinewatch.data.api.SafeApiRequest

import project.dheeraj.cinewatch.utils.CONSTANTS
import javax.inject.Inject



class NetworkRepository @Inject constructor(
    private val networkApi: NetworkService
) : SafeApiRequest() {

    // Get Movie Details
    suspend fun getMovieDetails(movie_id : Int) = apiRequest {
        networkApi.getMovieById(movie_id, CONSTANTS.API_KEY)
    }

    // Now Playing Movies
    suspend fun getNowPlayingMovie() = apiRequest {
        networkApi.getNowPlayingMovies(CONSTANTS.API_KEY)
    }



    // Upcoming Movies
    suspend fun getUpcomingMovie() = apiRequest {
        networkApi.getUpcomingMovies(CONSTANTS.API_KEY)
    }

    // Popular Movies
    suspend fun getPopularMovie() = apiRequest {
        networkApi.getPopularMovies(CONSTANTS.API_KEY)
    }

    // Top Rated Movies
    suspend fun getTopRatedMovie() = apiRequest {
        networkApi.getTopRatedMovies(CONSTANTS.API_KEY)
    }




}