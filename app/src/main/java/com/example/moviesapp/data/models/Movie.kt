package com.example.moviesapp.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
@Entity(tableName = "movie", indices =[Index(value = ["imdbID"], unique = true)] )

data class Movie(
    @ColumnInfo(name = "title" ) val  Title: String,
    @ColumnInfo(name = "year" ) val Year: String,
    @ColumnInfo(name = "rated" ) val Rated: String,
    @ColumnInfo(name = "released" ) val Released: String,
    @ColumnInfo(name = "runtime" ) val Runtime: String,
    @ColumnInfo(name = "genre" ) val Genre: String,
    @ColumnInfo(name = "director" ) val Director: String,
    @ColumnInfo(name = "writer" ) val Writer: String,
    @ColumnInfo(name = "actors" ) val Actors: String,
    @ColumnInfo(name = "plot" ) val Plot: String,
    @ColumnInfo(name = "language" ) val Language: String,
    @ColumnInfo(name = "country" ) val Country: String,
    @ColumnInfo(name = "awards" ) val Awards: String,
    @ColumnInfo(name = "poster" ) val Poster: String,
    @ColumnInfo(name = "ratings" ) val Ratings: String,
    @ColumnInfo(name = "metaScore" ) val Metascore: String,
    @ColumnInfo(name = "imdbRating" ) val imdbRating: String,
    @ColumnInfo(name = "imdbVotes" ) val imdbVotes: String,
    @PrimaryKey( ) val imdbID: String,
    @ColumnInfo(name = "type" ) val Type: String,
    @ColumnInfo(name = "dvd" ) val DVD: String,
    @ColumnInfo(name = "boxOffice" ) val BoxOffice: String,
    @ColumnInfo(name = "production" ) val Production: String,
    @ColumnInfo(name = "website" ) val Website: String,
    @ColumnInfo(name = "response" ) val Response: String
) : Parcelable