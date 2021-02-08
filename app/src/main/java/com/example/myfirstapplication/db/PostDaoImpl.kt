package com.example.myfirstapplication.db

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.myfirstapplication.post.Post

class PostDaoImpl(private val db: SQLiteDatabase) : PostDao {
    companion object {}

    object PostColumns {
        const val TABLE = "posts"
        const val COLUMN_ID = "id"
        const val COLUMN_CONTENT = "content"
        const val COLUMN_LIKES = "likes"
        const val COLUMN_SHARES = "shares"
        const val COLUMN_LIKED_BY_ME = "likedByMe"
        const val COLUMN_SHARED_BY_ME = "sharedByMe"
        const val COLUMN_VIEWS = "views"
        const val COLUMN_VIDEO = "video"
        val ALL_COLUMNS = arrayOf(
            COLUMN_ID,
            COLUMN_CONTENT,
            COLUMN_LIKES,
            COLUMN_SHARES,
            COLUMN_LIKED_BY_ME,
            COLUMN_SHARED_BY_ME,
            COLUMN_VIDEO,
            COLUMN_VIEWS
        )
    }

    override fun getAll(): List<Post> {
        val posts = mutableListOf<Post>()
        db.query(
            PostColumns.TABLE,
            PostColumns.ALL_COLUMNS,
            null,
            null,
            null,
            null,
            "${PostColumns.ALL_COLUMNS} DESC"
        ).use {
            while (it.moveToNext()) {
                posts.add(map(it))
            }
        }
        return posts
    }

    override fun save(post: Post): Post {
        val values = ContentValues().apply {
            if (post.id != 0L) {
                put(PostColumns.COLUMN_ID, post.id)
            }
            put(PostColumns.COLUMN_CONTENT, post.content)
            put(PostColumns.COLUMN_VIDEO, post.video)
        }
        val id = db.replace(PostColumns.TABLE, null, values)
        db.query(
            PostColumns.TABLE,
            PostColumns.ALL_COLUMNS,
            "${PostColumns.COLUMN_ID} = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        ).use {
            it.moveToNext()
            return map(it)
        }
    }

    override fun likeById(id: Long) {
        db.execSQL(
            """
            UPDATE posts SET
                likes = likes + CASE WHEN likedByMe THEN -1 ELSE 1 END,
                likedByMe = CASE WHEN likedByMe THEN 0 ELSE END
            WHERE id = ?;    
            """.trimIndent(), arrayOf(id)
        )
    }

    override fun shareById(id: Long) {
        db.execSQL(
            """
            UPDATE posts SET
                shares = shares + 1 END,
                likedByMe = 1 END
            WHERE id = ?;    
            """.trimIndent(), arrayOf(id)
        )
    }

    override fun removeById(id: Long) {
        db.delete(
            PostColumns.TABLE,
            "${PostColumns.COLUMN_ID} = ?",
            arrayOf(id.toString())
        )
    }
}

private fun map(cursor: Cursor): Post {
    with(cursor) {
        return Post(
            id = getLong(getColumnIndexOrThrow(PostDaoImpl.PostColumns.COLUMN_ID)),
            content = getString(getColumnIndexOrThrow(PostDaoImpl.PostColumns.COLUMN_CONTENT)),
            likes = getInt(getColumnIndexOrThrow(PostDaoImpl.PostColumns.COLUMN_LIKES)),
            shares = getInt(getColumnIndexOrThrow(PostDaoImpl.PostColumns.COLUMN_SHARES)),
            likedByMe = getInt(getColumnIndexOrThrow(PostDaoImpl.PostColumns.COLUMN_LIKED_BY_ME)) != 0,
            sharedByMe = getInt(getColumnIndexOrThrow(PostDaoImpl.PostColumns.COLUMN_SHARED_BY_ME)) != 0,
            views = getInt(getColumnIndexOrThrow(PostDaoImpl.PostColumns.COLUMN_VIEWS)),
            video = getString(getColumnIndexOrThrow(PostDaoImpl.PostColumns.COLUMN_VIDEO)),
        )
    }
}