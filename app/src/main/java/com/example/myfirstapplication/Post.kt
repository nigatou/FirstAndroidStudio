package com.example.myfirstapplication

data class Post (
    val id: Long,
    val likes: String,
    val shares: String,
    val likedByMe: Boolean,
    val sharedByMe: Boolean
)