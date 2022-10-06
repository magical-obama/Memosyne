package at.maximilian.memosyne.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Data Access Object for the Memo class.
 */
@Dao
interface MemoDao {
    @Query("SELECT * FROM memos")
    fun getAllMemos(): LiveData<List<Memo>>

    @Query("SELECT * FROM memos WHERE uid IN (:memoId)")
    fun getMemoById(memoId: Int): LiveData<Memo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMemo(memo: Memo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMemos(vararg memos: Memo)

    @Delete
    suspend fun delete(memo: Memo)
}