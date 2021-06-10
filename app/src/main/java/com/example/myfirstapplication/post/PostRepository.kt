package com.example.myfirstapplication.post

interface PostRepository {
    fun getAll(): List<Post>
    fun likeById(id: Long)
    fun dislikeById(id: Long)
    fun shareById(id: Long)
    fun removeById(id: Long)
    fun playVideo(post: Post)
    fun view(post: Post)
    fun save(post: Post)

    fun getAllAsync(callback: GetAllCallback)

    interface GetAllCallback {
        fun onSuccess(posts: List<Post>) {}
        fun onError(e: Exception) {}
    }
}