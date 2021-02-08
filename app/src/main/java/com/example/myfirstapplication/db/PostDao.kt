package com.example.myfirstapplication.db

import com.example.myfirstapplication.post.Post

interface PostDao {
    fun getAll(): List<Post>
    fun save(post: Post): Post
    fun likeById(id: Long)
    fun shareById(id: Long)
    fun removeById(id: Long)
}