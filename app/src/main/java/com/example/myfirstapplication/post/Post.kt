package com.example.myfirstapplication.post

data class Post (
    val id: Long,
    val content: String,
    val likes: String,
    val shares: String,
    val likedByMe: Boolean,
    val sharedByMe: Boolean
)