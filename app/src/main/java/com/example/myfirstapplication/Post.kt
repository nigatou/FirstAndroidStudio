package com.example.myfirstapplication

data class Post (
    val id: Long,
    val likes: Int,
    val shares: Int,
    val likedByMe: Boolean,
    val sharedByMe: Boolean
)