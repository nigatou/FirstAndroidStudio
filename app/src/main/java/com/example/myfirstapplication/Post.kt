package com.example.myfirstapplication

data class Post (
    val id: Long,
    var likes: Int = 999,
    var shares: Int = 999,
    var likedByMe: Boolean = false,
    var sharedByMe: Boolean = false
)