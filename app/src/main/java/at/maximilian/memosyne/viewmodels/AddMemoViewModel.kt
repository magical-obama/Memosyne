package at.maximilian.memosyne.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.maximilian.memosyne.db.Memo
import at.maximilian.memosyne.db.MemoRepository
import at.maximilian.memosyne.fragments.AddMemoFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * [ViewModel] for the [AddMemoFragment]
 */
class AddMemoViewModel(application: Application) : AndroidViewModel(application) {
    // TODO: Implement the ViewModel
    var title: String = ""
    var content: String = ""
    private val repository: MemoRepository
    private val allMemos: LiveData<List<Memo>>

    init {
        repository = MemoRepository(application)
        allMemos = repository.getAllMemos()
    }

    fun getAllMemos() = allMemos
    fun insertMemo(memo: Memo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMemo(memo)
        }
    }
}