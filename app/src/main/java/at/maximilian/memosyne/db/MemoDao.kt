package at.maximilian.memosyne.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the Memo class.
 */
@Dao
interface MemoDao {
    @Query("SELECT * FROM memo")
    fun getAllMemos(): Flow<List<Memo>>

    @Query("SELECT * FROM memo WHERE uid IN (:memoId)")
    fun getMemoById(memoId: Int): Flow<Memo>

    @Insert
    fun insertMemo(memo: Memo)

    @Insert
    fun insertAllMemos(vararg memos: Memo)

    @Delete
    fun delete(memo: Memo)
}