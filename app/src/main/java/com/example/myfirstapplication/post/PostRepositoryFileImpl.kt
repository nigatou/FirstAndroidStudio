package com.example.myfirstapplication.post

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PostRepositoryFileImpl(
        private val context: Context
) : PostRepository {
    private val gson = Gson()
    private val type = TypeToken.getParameterized(List::class.java, Post::class.java).type
    private val filename = "posts.json"
    private var nextId = 3L
    private var posts = emptyList<Post>()
    private val data = MutableLiveData(posts)

    private fun sync() {
        context.openFileOutput(filename, Context.MODE_PRIVATE).bufferedWriter().use {
            it.write(gson.toJson(posts))
        }
    }

    init {
        val file = context.filesDir.resolve(filename)
        if (file.exists()) {
            context.openFileInput(filename).bufferedReader().use {
                posts = gson.fromJson(it, type)
                data.value = posts
            }
        } else {
            sync()
        }
    }

    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map {
            when {
                it.id != id -> it
                it.likedByMe -> {
                    it.copy(likes = it.likes - 1, likedByMe = false)
                }
                else -> {
                    it.copy(likes = it.likes + 1, likedByMe = true)
                }
            }
        }
        data.value = posts
        sync()
    }

    override fun shareById(id: Long) {
        posts = posts.map {
            if (it.id != id) {
                it
            } else {
                it.copy(shares = it.shares + 1, sharedByMe = true)
            }
        }
        data.value = posts
        sync()
    }

    override fun view(post: Post) {
        sync()
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
        sync()
    }

    override fun playVideo(post: Post) {
        sync()
    }

    override fun save(post: Post) {
        if (post.id == 0L) {
            posts = listOf(
                    post.copy(
                            id = nextId++,
                            likes = 1,
                            shares = 1,
                            likedByMe = false,
                            sharedByMe = false,
                            views = 1,
                    )
            ) + posts
            data.value = posts
            sync()
            return
        }

        posts = posts.map {
            if (it.id != post.id) {
                it
            } else {
                it.copy(content = post.content, video = post.video)
            }
        }
        data.value = posts
        sync()
    }
}