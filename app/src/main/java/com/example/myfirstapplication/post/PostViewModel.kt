package com.example.myfirstapplication.post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private val empty = Post(
        id=0,
        content = "",
        likes = 0,
        shares = 0,
        sharedByMe = false,
        likedByMe = false,
        views = 0,
)

class PostViewModel : ViewModel() {

    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    val edited = MutableLiveData(empty)

    fun likeById(id: Long) = repository.likeById(id)
    fun shareById(id: Long) = repository.shareById(id)
    fun removeById(id: Long) = repository.removeById(id)

    fun save(cancel: Boolean) {
        if (!cancel) {
            edited.value?.let {
                repository.save(it)
            }
        }
        edited.value = empty
    }

    fun edit(post: Post) {
        edited.value = post

    }

    fun changeContent(content: String) {

        val text = content.trim()
        if (edited.value?.content == text) {
            return
        }
        edited.value = edited.value?.copy(content = text)
    }
}