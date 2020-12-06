package com.example.myfirstapplication

import android.view.View
import androidx.lifecycle.LiveData

interface PostRepository {
    fun get(): LiveData<Post>
    fun like(): View.OnClickListener?
    fun share(): View.OnClickListener?
}