package com.example.myfirstapplication.adapter

import com.example.myfirstapplication.post.Post

interface OnInteractionListener {
    fun onLike(post: Post) {}
    fun onShare(post: Post) {}
    fun onEdit(post: Post) {}
    fun onRemove(post: Post) {}
    fun onPlayVideo(post: Post) {}
    fun onView(post: Post) {}
}