package at.maximilian.memosyne.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.maximilian.memosyne.db.Memo
import at.maximilian.memosyne.db.MemoRepository
import at.maximilian.memosyne.fragments.AddMemoFragment
import kotlinx.coroutines.launch

/**
 * [ViewModel] for the [AddMemoFragment]
 */
class AddMemoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MemoRepository(application)

    fun insertMemo(memo: Memo) {
        viewModelScope.launch {
            repository.insertMemo(memo)
        }
    }

    fun getMemoById(memoId: Int) = repository.getMemoById(memoId)
}