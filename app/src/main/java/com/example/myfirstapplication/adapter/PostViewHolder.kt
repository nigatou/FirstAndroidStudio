package com.example.myfirstapplication.adapter

import android.util.Log
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.*
import com.example.myfirstapplication.post.Post
import com.example.myfirstapplication.databinding.CardPostBinding

class PostViewHolder(
        private val binding: CardPostBinding,
        private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            content.text = post.content
            like.text = ConvertNumberService.convertNumber(post.likes)
            share.text = ConvertNumberService.convertNumber(post.shares)
            like.isChecked = post.likedByMe
            share.isChecked = post.sharedByMe
            view.text = ConvertNumberService.convertNumber(post.views)

            like.setOnClickListener{
                onInteractionListener.onLike(post)
            }

            share.setOnClickListener{
                onInteractionListener.onShare(post)
            }

            imageButton.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_menu)
                    setOnMenuItemClickListener { item ->
                        when(item.itemId) {
                            R.id.remove -> {
                                onInteractionListener.onRemove(post)
                                true
                            }
                            R.id.edit -> {
                                onInteractionListener.onEdit(post)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }
        }
    }
}