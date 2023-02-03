package project.dheeraj.cinewatch.data.api

import project.dheeraj.cinewatch.data.model.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface NetworkService {

    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movie_id: Int,
        @Query ("api_key") apiKey : String
    ): Response<Movie>

    @GET("movie/{movie_id}/images")
    suspend fun getMovieImage(
        @Path("movie_id") movie_id: Int,
        @Query ("api_key") apiKey : String
    ): Response<Movie>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query ("api_key") apiKey : String,
        @Query("page") page : Int = 1
    ): Response<MovieResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query ("api_key") apiKey : String,
        @Query("page") page : Int = 1
    ): Response<MovieResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query ("api_key") apiKey : String,
        @Query("page") page : Int = 1
    ): Response<MovieResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query ("api_key") apiKey : String
    ): Response<MovieResponse>





}