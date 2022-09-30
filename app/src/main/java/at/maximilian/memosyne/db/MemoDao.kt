package at.maximilian.memosyne.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MemoDao {
    @Query("SELECT * FROM memo")
    fun getAll(): List<Memo>

    @Query("SELECT * FROM memo WHERE uid IN (:memoIds)")
    fun loadAllByIds(memoIds: IntArray): List<Memo>

    @Insert
    fun insertMemo(memo: Memo)

    @Insert
    fun insertAllMemos(vararg memos: Memo)

    @Delete
    fun delete(memo: Memo)
}