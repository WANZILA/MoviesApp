package com.example.moviesapp.data.sources.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.moviesapp.data.models.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Query("SELECT * FROM movie WHERE imdbID = :imdbID")
    suspend fun getMovieById(imdbID: String): Movie?

    @Query("SELECT * FROM movie")
    suspend fun getAllMovies(): List<Movie>

    @Query("DELETE FROM movie WHERE imdbID = :imdbID")
    suspend fun deleteMovieById(imdbID: String)

    @Query("DELETE FROM movie")
    suspend fun deleteAllMovies()

    @Query("SELECT COUNT(*) FROM movie")
    suspend fun getMovieCount(): Int

    @Query("SELECT * FROM movie WHERE title LIKE '%' || :query || '%'")
    suspend fun searchMovies(query: String): List<Movie>

    @Delete()
    fun deleteMovie(movie: Movie)

    @Update
    fun updateMovie(movie: Movie)




}