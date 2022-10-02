package at.maximilian.memosyne.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * The main class for a Memo
 * @param uid Do not set. Set automatically by room
 * @param title The memo's title
 * @param content The memo's content
 */
@Entity
data class Memo(
    @PrimaryKey(autoGenerate = true) val uid: Int?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String
)
