package at.maximilian.memosyne.viewmodels

import android.app.Application
import androidx.core.view.MenuProvider
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.maximilian.memosyne.db.Memo
import at.maximilian.memosyne.db.MemoRepository
import at.maximilian.memosyne.fragments.OverviewFragment
import kotlinx.coroutines.launch

/**
 * [ViewModel] for the [OverviewFragment]
 */
class OverviewViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MemoRepository(application)
    val allMemos = repository.getAllMemos()
    fun deleteMemo(memo: Memo) {
        viewModelScope.launch {
            repository.delete(memo)
        }
    }
}