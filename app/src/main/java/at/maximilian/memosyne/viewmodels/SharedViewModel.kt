package at.maximilian.memosyne.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import at.maximilian.memosyne.db.Memo

class SharedViewModel : ViewModel() {
    var selected: MutableLiveData<Memo>? = null

    fun select(item: Memo? = null) {
        selected = if (item == null) {
            null
        } else {
            MutableLiveData(item)
        }
    }
}
