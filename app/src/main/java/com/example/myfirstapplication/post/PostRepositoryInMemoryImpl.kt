package com.example.myfirstapplication.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PostRepositoryInMemoryImpl: PostRepository {

    private var posts = listOf(
        Post(
            id = 1L,
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помооаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен -> http://netolo.gy/fyb",
            likes = 800,
            shares = 998,
            likedByMe = false,
            sharedByMe = false,
            views = 1,
            video = ""
        ),
        Post(
            id = 2L,
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помооаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен -> http://netolo.gy/fyb",
                likes = 800,
                shares = 800,
                likedByMe = false,
                sharedByMe = false,
                views = 1,
                video = "http://www.google.com"
        )
    )

    private var nextId = 3L

    private val data = MutableLiveData(posts)

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
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }

    override fun playVideo(post: Post) {
        return
    }

    override fun save(post: Post) {
        if (post.id == 0L) {
            posts = listOf(
                    post.copy(
                            id = nextId++,
                            likes = 0,
                            shares = 0,
                            likedByMe = false,
                            sharedByMe = false,
                            views = 1,
                    )
            ) + posts
            data.value = posts
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
    }
}