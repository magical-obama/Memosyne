package at.maximilian.memosyne.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    @Query("SELECT * FROM memo")
    fun getAllMemos(): LiveData<List<Memo>>

    @Query("SELECT * FROM memo WHERE uid IN (:memoId)")
    fun getMemoById(memoId: Int): Memo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMemo(memo: Memo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMemos(vararg memos: Memo)

    @Delete
    suspend fun delete(memo: Memo)
}