package at.maximilian.memosyne.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import at.maximilian.memosyne.App
import at.maximilian.memosyne.db.Memo
import at.maximilian.memosyne.db.MemoRepository

class AddMemoViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var title: String = ""
    var content: String = ""
    val memos: List<Memo> =
        MemoRepository(App().database.memoDao()).getAllMemos()
}