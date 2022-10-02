package at.maximilian.memosyne.viewmodels

import androidx.core.view.MenuProvider
import androidx.lifecycle.ViewModel
import at.maximilian.memosyne.App
import at.maximilian.memosyne.db.Memo

class OverviewViewModel : ViewModel() {
    val memos: List<Memo> = App().database.memoDao().getAllMemos()
    var menuProvider: MenuProvider? = null
}