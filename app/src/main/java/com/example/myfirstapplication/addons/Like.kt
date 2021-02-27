package com.example.myfirstapplication.addons

data class Like (
    val userId: Long,
    val userName: String,
    val postId: Long,
    val postAuthor: String
)