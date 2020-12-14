package com.example.myfirstapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.databinding.ActivityMainBinding
import com.example.myfirstapplication.databinding.CardPostBinding

typealias OnLikeListener = (post: Post) -> Unit
typealias OnShareListener = (post: Post) -> Unit

class PostsAdapter(
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener
) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
            val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PostViewHolder(binding, onLikeListener, onShareListener)
        }

        override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
            val post = getItem(position)
            holder.bind(post)
        }
}

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnLikeListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            numberOfLikes.text = post.likes.toString()
            numberOfShares.text = post.shares.toString()

            like.setImageResource(
                if (post.likedByMe) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
            )
            like.setOnClickListener{
                onLikeListener(post)
            }

            share.setImageResource(
                if (post.sharedByMe) R.drawable.ic_baseline_shared_24 else R.drawable.ic_baseline_share_24
            )
            like.setOnClickListener{
                onShareListener(post)
            }
        }
    }
}