package com.example.myfirstapplication.post

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.myfirstapplication.db.AppDb

private val empty = Post(
    id = 0L,
    content = "",
    likes = 0,
    shares = 0,
    sharedByMe = false,
    likedByMe = false,
    views = 0,
    video = ""
)

class PostViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PostRepository = PostRepositoryImpl(
        AppDb.getInstance(context = application).postDao()
    )
    val data = repository.getAll()
    private val edited = MutableLiveData(empty)

    fun likeById(id: Long) = repository.likeById(id)
    fun shareById(id: Long) = repository.shareById(id)
    fun removeById(id: Long) = repository.removeById(id)
    fun playVideo(post: Post) = repository.playVideo(post)
    fun view(post: Post) = repository.view(post)

    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }

    fun edit(post: Post) {
        edited.value = post
    }

    fun getPost(): Post? {
        return edited.value
    }

    fun changeContent(content: String, video: String) {
        val contentString = content.trim()
        val videoString = video.trim()
        if (edited.value?.content == contentString && edited.value?.video == videoString) {
            return
        }
        edited.value = edited.value?.copy(content = contentString, video = videoString)
    }
}