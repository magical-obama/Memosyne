package at.maximilian.memosyne.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import at.maximilian.memosyne.App

/**
 * The application-wide database class
 */
@Database(entities = [Memo::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memoDao(): MemoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Get the App's database (only used by [App])
         * @param context Application context
         */
        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "memo-database"
                ).build().also { INSTANCE = it }
            }
    }
}