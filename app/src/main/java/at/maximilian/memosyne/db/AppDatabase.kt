package at.maximilian.memosyne.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import at.maximilian.memosyne.App

@Database(entities = [Memo::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memoDao(): MemoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    App().applicationContext,
                    AppDatabase::class.java,
                    "memo-database"
                ).build().also { INSTANCE = it }
            }
    }
}