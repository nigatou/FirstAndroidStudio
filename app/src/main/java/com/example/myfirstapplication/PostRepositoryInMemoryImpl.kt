package com.example.myfirstapplication

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PostRepositoryInMemoryImpl: PostRepository {

    private var post = Post(
        id = 1,
        likes = 999,
        shares = 999,
        likedByMe = false,
        sharedByMe = false
    )

    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data

    override fun like(): View.OnClickListener? {
        post = if (post.likedByMe) {
            post.copy(likes = post.likes - 1, likedByMe = !post.likedByMe)
        } else {
            post.copy(likes = post.likes + 1, likedByMe = !post.likedByMe)
        }
        data.value = post
        return null
    }

    override fun share(): View.OnClickListener? {
        post = post.copy(shares = post.shares + 1, sharedByMe = true)
        data.value = post
        return null
    }
}