package at.maximilian.memosyne.db

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

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

    suspend fun insertMemo(memo: Memo) = memoDao.insertMemo(memo)

    suspend fun insertAllMemos(vararg memos: Memo) = memoDao.insertAllMemos(*memos)

    suspend fun delete(memo: Memo) = memoDao.delete(memo)
}