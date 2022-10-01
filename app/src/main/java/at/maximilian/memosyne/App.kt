package at.maximilian.memosyne

import android.app.Application
import at.maximilian.memosyne.db.AppDatabase

class App : Application() {
    val database by lazy { AppDatabase.getInstance() }
}