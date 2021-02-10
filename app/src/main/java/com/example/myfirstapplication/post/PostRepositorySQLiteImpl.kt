package com.example.myfirstapplication.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myfirstapplication.db.PostDao

class PostRepositorySQLiteImpl(
        private val dao: PostDao
) : PostRepository {
    private var posts = emptyList<Post>()
    private val data = MutableLiveData(posts)

    init {
        posts = dao.getAll()
        data.value = posts
    }

    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id: Long): Int {
        val likesOfThePost = 0
        posts = posts.map {
            if (it.id != id) {
                it
            } else {
                dao.likeById(it.id)
                it.copy(
                        likedByMe = !it.likedByMe,
                        likes = if (it.likedByMe) {
                            it.likes - 1
                        } else {
                            it.likes + 1
                        }
                )
            }
        }
        data.value = posts
        return likesOfThePost
    }

    override fun shareById(id: Long): Int {

        val sharesOfThePost = 0

        posts = posts.map {
            if (it.id != id) {
                it
            } else {
                dao.shareById(it.id)
                it.copy(
                        sharedByMe = true,
                        shares = it.shares + 1
                )
            }
        }
        data.value = posts
        return sharesOfThePost
    }

    override fun view(post: Post) {}

    override fun removeById(id: Long) {
        dao.removeById(id)
        posts = posts.filter {
            it.id != id
        }
        data.value = posts
    }

    override fun playVideo(post: Post) {}

    override fun save(post: Post) {
        val id = post.id
        val saved = dao.save(post)
        posts = if (id == 0L) {
            listOf(saved) + posts
        } else {
            posts.map {
                if (it.id != id) it else saved
            }
        }
        data.value = posts
    }
}