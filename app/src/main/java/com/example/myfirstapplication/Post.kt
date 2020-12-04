package com.example.myfirstapplication

data class Post (
    val id: Long,
    val likes: Int = 999,
    val shares: Int = 999,
    val likedByMe: Boolean = false,
    val sharedByMe: Boolean = false
)