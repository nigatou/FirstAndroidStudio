package com.example.myfirstapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myfirstapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()

        viewModel.data.observe(this, {post ->
            with(binding) {
                if (post.likedByMe) {
                    like.setImageResource(R.drawable.ic_baseline_favorite_24)
                    numberOfLikes.text = ConvertNumberService.convertNumber(post.likes)
                } else {
                    like.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    numberOfLikes.text = ConvertNumberService.convertNumber(post.likes)
                }
                if (post.sharedByMe) {
                    share.setImageResource(R.drawable.ic_baseline_shared_24)
                    numberOfShares.text = ConvertNumberService.convertNumber(post.shares)
                }
            }
        })
        binding.like.setOnClickListener(
            viewModel.like()
        )
        binding.share.setOnClickListener(
            viewModel.share()
        )
    }
}