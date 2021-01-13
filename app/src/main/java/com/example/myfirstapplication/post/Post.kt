package com.example.myfirstapplication.post

data class Post (
    val id: Long,
    val content: String,
    val likes: Int,
    val shares: Int,
    val likedByMe: Boolean,
    val sharedByMe: Boolean,
    val views: Int,
    val video: String
)