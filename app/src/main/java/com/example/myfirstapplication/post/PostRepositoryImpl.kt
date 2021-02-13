package com.example.myfirstapplication.post

import androidx.lifecycle.Transformations
import com.example.myfirstapplication.db.PostDao
import com.example.myfirstapplication.room.PostEntity

class PostRepositoryImpl(
    private val dao: PostDao
) : PostRepository {

    override fun getAll() = Transformations.map(dao.getAll()) { list ->
        list.map {
            Post(
                it.id,
                it.content,
                it.likes,
                it.shares,
                it.likedByMe,
                it.sharedByMe,
                it.views,
                it.video
            )
        }
    }

    override fun likeById(id: Long) {
        dao.likeById(id)
    }

    override fun shareById(id: Long) {
        dao.shareById(id)
    }

    override fun view(post: Post) {}

    override fun removeById(id: Long) {
        dao.removeById(id)
    }

    override fun playVideo(post: Post) {}

    override fun save(post: Post) {
        dao.save(PostEntity.fromDto(post))
    }
}