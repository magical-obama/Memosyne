package at.maximilian.memosyne.db

import androidx.lifecycle.LiveData
import at.maximilian.memosyne.App

class MemoRepository {
    private val memoDao: MemoDao
    private val memoData: LiveData<List<Memo>>

    init {
        val db = App().database
        this.memoDao = db.memoDao()
        this.memoData = db.memoDao().getAll()
    }

    fun getAllData(): LiveData<List<Memo>> {
        return memoData
    }

    suspend fun insert() {

    }
}