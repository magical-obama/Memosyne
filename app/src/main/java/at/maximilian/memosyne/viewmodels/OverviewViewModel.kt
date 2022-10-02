package at.maximilian.memosyne.viewmodels

import android.app.Application
import androidx.core.view.MenuProvider
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import at.maximilian.memosyne.db.Memo
import at.maximilian.memosyne.db.MemoRepository
import at.maximilian.memosyne.fragments.OverviewFragment

/**
 * [ViewModel] for the [OverviewFragment]
 */
class OverviewViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MemoRepository(application)
    val allMemos = repository.getAllMemos()

    var menuProvider: MenuProvider? = null
}