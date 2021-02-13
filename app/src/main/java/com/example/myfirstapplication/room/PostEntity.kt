package com.example.myfirstapplication.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myfirstapplication.post.Post

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val content: String,
    val likes: Int = 0,
    val shares: Int = 0,
    val likedByMe: Boolean,
    val sharedByMe: Boolean,
    val views: Int = 0,
    val video: String
) {
    companion object {
        fun fromDto(post: Post): PostEntity {
            return PostEntity(
                post.id,
                post.content,
                post.likes,
                post.shares,
                post.likedByMe,
                post.sharedByMe,
                post.views,
                post.video
            )
        }
    }
}