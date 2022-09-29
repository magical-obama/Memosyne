package at.maximilian.memosyne.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Memo(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "content") val content: String?
)
