package com.example.moviesapp.data.sources.local

import android.content.Context
import androidx.room.*
import com.example.moviesapp.data.models.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class AppDatabase  : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                                                context.applicationContext,
                                                AppDatabase::class.java,
                                                "movie_database"
                                            ).fallbackToDestructiveMigration(false)
                            .build()
                    }
                }

            }
            return INSTANCE
        }
    }
}
