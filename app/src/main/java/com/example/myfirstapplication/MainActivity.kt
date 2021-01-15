package com.example.myfirstapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.myfirstapplication.adapter.OnInteractionListener
import com.example.myfirstapplication.adapter.PostsAdapter
import com.example.myfirstapplication.post.PostViewModel
import com.example.myfirstapplication.databinding.ActivityMainBinding
import com.example.myfirstapplication.post.Post

class MainActivity : AppCompatActivity() {

    val viewModel: PostViewModel by viewModels()

    var editablePost = Post(
            id=0L,
            content = "",
            likes = 0,
            shares = 0,
            sharedByMe = false,
            likedByMe = false,
            views = 0,
            video = ""
    )

    private val request = RequestCode()

    private fun addPost() {
        val intent = Intent(applicationContext, NewPostActivity::class.java)
        startActivityForResult(intent, 1)
    }

    private fun editPost(post: Post) {
        val intent = Intent(applicationContext, NewPostActivity::class.java)
        intent.putExtra("edit", true)
        intent.putExtra("postId", post.id)
        intent.putExtra("postContent", post.content)
        intent.putExtra("postVideo", post.video)

        editablePost = post

        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            when (resultCode) {
                request.empty -> {
                    Log.d("Result Code:", "0")
                }
                request.add -> {
                    var postContent = ""
                    var postVideo = ""
                    data?.getStringExtra("postContent")?.let {
                        postContent = it
                    }
                    data?.getStringExtra("postVideo")?.let {
                        postVideo = it
                    }
                    viewModel.changeContent(postContent, postVideo)
                    viewModel.save()
                }
                request.edit -> {
                    var postContent = ""
                    var postVideo = ""
                    data?.getStringExtra("postContent")?.let {
                        postContent = it
                    }
                    data?.getStringExtra("postVideo")?.let {
                        postVideo = it
                    }

                    editablePost = editablePost.copy(content = postContent, video = postVideo)

                    viewModel.edit(editablePost)
                    viewModel.save()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PostsAdapter(object : OnInteractionListener {
            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onShare(post: Post) {
                viewModel.shareById(post.id)
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(intent, getString(R.string.chooser_share_post))
                startActivity(shareIntent)
            }

            override fun onEdit(post: Post) {
                editPost(post)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onPlayVideo(post: Post) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(post.video))
                startActivity(intent)
            }
        })

        binding.list.adapter = adapter

        viewModel.data.observe(this, { posts ->
            adapter.submitList(posts)
        })

        binding.fab.setOnClickListener {
            addPost()
        }
    }
}