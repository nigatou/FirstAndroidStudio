package com.example.myfirstapplication.post

import androidx.lifecycle.LiveData

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Long)
    fun shareById(id: Long)
    fun removeById(id: Long)
    fun playVideo(post: Post)
    fun view(post: Post)
    fun save(post: Post)
}