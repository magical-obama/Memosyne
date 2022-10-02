package at.maximilian.memosyne

import android.app.Application
import at.maximilian.memosyne.db.AppDatabase

/**
 * The main application class for accessing a database instance and context
 */
class App : Application() {
    val database by lazy { AppDatabase.getInstance(this.applicationContext) }
}