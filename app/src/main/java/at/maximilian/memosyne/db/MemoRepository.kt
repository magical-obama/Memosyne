package at.maximilian.memosyne.db

import androidx.lifecycle.LiveData
import at.maximilian.memosyne.App

/**
 * Repository for the Memo class.
 */
class MemoRepository(private val memoDao: MemoDao) {
    fun getAllMemos() = memoDao.getAllMemos()

    fun getMemoById(memoId: Int) = memoDao.getMemoById(memoId)

    fun insertMemo(memo: Memo) = memoDao.insertMemo(memo)

    fun insertAllMemos(vararg memos: Memo) = memoDao.insertAllMemos(*memos)

    fun delete(memo: Memo) = memoDao.delete(memo)
}