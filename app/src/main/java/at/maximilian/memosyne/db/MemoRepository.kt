package at.maximilian.memosyne.db

import android.app.Application
import androidx.lifecycle.LiveData

/**
 * Repository for the Memo class.
 */
class MemoRepository(application: Application) {
    private val memoDao: MemoDao
    private val allMemos: LiveData<List<Memo>>

    init {
        val db = AppDatabase.getInstance(application)
        memoDao = db.memoDao()
        allMemos = memoDao.getAllMemos()
    }

    fun getAllMemos() = allMemos

    fun getMemoById(memoId: Int) = memoDao.getMemoById(memoId)

    fun insertMemo(memo: Memo) = memoDao.insertMemo(memo)

    fun insertAllMemos(vararg memos: Memo) = memoDao.insertAllMemos(*memos)

    fun delete(memo: Memo) = memoDao.delete(memo)
}