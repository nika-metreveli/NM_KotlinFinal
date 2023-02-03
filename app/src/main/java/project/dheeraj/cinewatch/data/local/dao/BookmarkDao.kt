package project.dheeraj.cinewatch.data.local.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import project.dheeraj.cinewatch.data.model.Movie
import project.dheeraj.cinewatch.data.model.MovieDB
import project.dheeraj.cinewatch.utils.CONSTANTS
import project.dheeraj.cinewatch.utils.CONSTANTS.Companion.TABLE_NAME


@Dao
interface BookmarkDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(Movie: MovieDB)


    @Delete
    fun removeMovie(movie: MovieDB)



    @Query("Select * from $TABLE_NAME")
    fun getAllBookmark() : List<MovieDB>


    @Query("SELECT EXISTS (SELECT 1 FROM $TABLE_NAME WHERE id = :id)")
    fun bookmarkExist(id: Int): Boolean


}