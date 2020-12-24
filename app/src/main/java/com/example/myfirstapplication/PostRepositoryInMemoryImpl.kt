package com.example.myfirstapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PostRepositoryInMemoryImpl: PostRepository {

    private var posts = listOf(
        Post(
            id = 1,
            likes = "999",
            shares = "999",
            likedByMe = false,
            sharedByMe = false
        ),
        Post(
            id = 2,
            likes = "999",
            shares = "999",
            likedByMe = false,
            sharedByMe = false
        )
    )

    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else if (it.likedByMe) it.copy(likes = ConvertNumberService.convertNumber(it.likes.toInt() - 1)!!, likedByMe = !it.likedByMe) else it.copy(likes = ConvertNumberService.convertNumber(it.likes.toInt() + 1)!!, likedByMe = !it.likedByMe)
        }
        data.value = posts
    }

    override fun shareById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else if (!it.sharedByMe)  it.copy(shares = ConvertNumberService.convertNumber(it.likes.toInt() + 1)!!, sharedByMe = true) else it
        }
        data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }
}