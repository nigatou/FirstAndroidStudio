package com.example.myfirstapplication.post

import androidx.lifecycle.LiveData

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Long): Int
    fun shareById(id: Long): Int
    fun removeById(id: Long)
    fun playVideo(post: Post)
    fun view(post: Post)
    fun save(post: Post)
}