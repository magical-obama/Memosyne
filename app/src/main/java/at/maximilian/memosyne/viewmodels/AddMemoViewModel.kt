package at.maximilian.memosyne.viewmodels

import androidx.lifecycle.ViewModel
import at.maximilian.memosyne.App
import at.maximilian.memosyne.db.Memo
import at.maximilian.memosyne.db.MemoRepository
import at.maximilian.memosyne.fragments.AddMemoFragment

/**
 * [ViewModel] for the [AddMemoFragment]
 */
class AddMemoViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var title: String = ""
    var content: String = ""
    val memos: List<Memo> =
        MemoRepository(App().database.memoDao()).getAllMemos()
}