package at.maximilian.memosyne.viewmodels

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import at.maximilian.memosyne.App
import at.maximilian.memosyne.db.Memo
import at.maximilian.memosyne.db.MemoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject internal constructor(
    memoRepository: MemoRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val memos: LiveData<List<Memo>>

    init {
        this.memos = memoRepository.getAllMemos().asLiveData()
    }
}