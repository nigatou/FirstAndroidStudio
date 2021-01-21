package com.example.myfirstapplication.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myfirstapplication.R
import com.example.myfirstapplication.RequestCode
import com.example.myfirstapplication.adapter.OnInteractionListener
import com.example.myfirstapplication.adapter.PostsAdapter
import com.example.myfirstapplication.post.PostViewModel
import com.example.myfirstapplication.databinding.RecyclerViewBinding
import com.example.myfirstapplication.post.Post

class RecyclerView : Fragment(R.layout.recycler_view) {

    /*
    private val viewModel: PostViewModel by viewModels(
            ownerProducer = ::requireParentFragment
    )

    private var editablePost = Post(
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
        val intent = Intent()
        startActivityForResult(intent, 1)
        findNavController().navigate(R.id.action_recyclerView_to_newPostView)
    }

    private fun editPost(post: Post) {
        val intent = Intent()
        intent.putExtra("edit", true)
        intent.putExtra("postId", post.id)
        intent.putExtra("postContent", post.content)
        intent.putExtra("postVideo", post.video)

        editablePost = post

        startActivityForResult(intent, 1)
        findNavController().navigate(R.id.action_recyclerView_to_newPostView)
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

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?)
    : View {
        val binding = RecyclerViewBinding.inflate(
                inflater,
                container,
                false
        )

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
                viewModel.playVideo(post)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(post.video))
                startActivity(intent)
            }
        })

        binding.list.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner, { posts ->
            adapter.submitList(posts)
        })

        binding.fab.setOnClickListener {
            addPost()
        }
        return binding.root
    }

     */
}