package com.example.myfirstapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myfirstapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private fun convertNumber(num: Int): String? {
        val number = num.toString()
        when (num) {
            in 1..999 -> {
                return number
            }
            in 1000..1099 -> {
                return number[0] + "K"
            }
            in 1100..9999 -> {
                return number[0] + "." + number[1] + "K"
            }
            in 10000..999999 -> {
                return number[0] + "K"
            }
            in 1000000..1099999 -> {
                return number[0] + "M"
            }
            in 1100000..9999999 -> {
                return number[0] + "." + number[1] + "M"
            }
        }
        return null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
                id = 1,
                likes = 998,
                shares = 998,
                likedByMe = false,
                sharedByMe = false
        )
        with(binding) {
            numberOfLikes.text = convertNumber(post.likes)
            numberOfShares.text = convertNumber(post.shares)

            if (post.likedByMe) {
                like.setImageResource(R.drawable.ic_baseline_favorite_24)
            }
            
            like.setOnClickListener {
                post.likedByMe = !post.likedByMe

                if (post.likedByMe) {
                    like.setImageResource(R.drawable.ic_baseline_favorite_24)
                    numberOfLikes.text = convertNumber(++post.likes)
                } else {
                    like.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    numberOfLikes.text = convertNumber(--post.likes)
                }
            }

            if (post.sharedByMe) {
                share.setImageResource(R.drawable.ic_baseline_shared_24)
            }

            share.setOnClickListener {
                post.sharedByMe = true
                share.setImageResource(R.drawable.ic_baseline_shared_24)
                numberOfShares.text = convertNumber(++post.shares)
            }
        }
    }
}