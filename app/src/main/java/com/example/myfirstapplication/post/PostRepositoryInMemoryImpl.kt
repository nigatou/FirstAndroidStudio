package com.example.myfirstapplication.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myfirstapplication.ConvertNumberService

class PostRepositoryInMemoryImpl: PostRepository {

    private var posts = listOf(
        Post(
            id = 1,
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помооаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен",
            likes = "800",
            shares = "800",
            likedByMe = false,
            sharedByMe = false
        ),
        Post(
            id = 2,
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помооаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен",
            likes = "800",
            shares = "800",
            likedByMe = false,
            sharedByMe = false
        )
    )

    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else if (it.likedByMe) it.copy(likes = ConvertNumberService.convertNumber(it.likes.toInt() - 1)!!, likedByMe = false) else it.copy(likes = ConvertNumberService.convertNumber(it.likes.toInt() + 1)!!, likedByMe = true)
        }
        data.value = posts
    }

    override fun shareById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(shares = ConvertNumberService.convertNumber(it.shares.toInt() + 1)!!, sharedByMe = true)
        }
        data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }

    override fun save(post: Post) {
        if (post.id == 0L) {
            posts = listOf(
                    post.copy(
                            id = post.id + 1,
                            likes = "0",
                            shares = "0",
                            likedByMe = false,
                            sharedByMe = false
                    )
            ) + posts
            data.value = posts
            return
        }

        posts = posts.map {
            if (it.id != post.id) it else it.copy(content = post.content)
        }
        data.value = posts
    }
}