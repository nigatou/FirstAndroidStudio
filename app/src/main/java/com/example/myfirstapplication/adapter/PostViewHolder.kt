package com.example.myfirstapplication.adapter

import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.*
import com.example.myfirstapplication.databinding.CardPostFragmentBinding
import com.example.myfirstapplication.post.Post
import com.example.myfirstapplication.service.ConvertNumberService

class PostViewHolder(
        private val binding: CardPostFragmentBinding,
        private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            content.text = post.content
            published.text = post.published
            like.text = ConvertNumberService.convertNumber(post.likes)
            share.text = ConvertNumberService.convertNumber(post.shares)
            like.isChecked = post.likedByMe
            share.isChecked = post.sharedByMe
            view.text = ConvertNumberService.convertNumber(post.views)
            if (post.video != "") {
                contentVideo.visibility = View.VISIBLE
                playVideo.visibility = View.VISIBLE
                contentLink.visibility = View.VISIBLE
                contentLink.text = post.video

                playVideo.setOnClickListener {
                    onInteractionListener.onPlayVideo(post)
                }
            }

            content.setOnClickListener {
                onInteractionListener.onView(post)
            }

            contentVideo.setOnClickListener {
                onInteractionListener.onView(post)
            }

            contentLink.setOnClickListener {
                onInteractionListener.onView(post)
            }

            like.setOnClickListener {
                onInteractionListener.onLike(post)
            }

            share.setOnClickListener {
                onInteractionListener.onShare(post)
            }

            imageButton.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_menu)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
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