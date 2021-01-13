package com.example.myfirstapplication

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.myfirstapplication.databinding.ActivityNewPostBinding

class NewPostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            val extras = intent.extras

            if (extras != null) {
                content.setText(extras.getString("postContent"))
                link.setText(extras.getString("postVideo"))
                contentLink.text = extras.getString("postVideo")
                if (extras.getString("postVideo") != "") {
                    contentVideo.visibility = View.VISIBLE
                    playVideo.visibility = View.VISIBLE
                    contentLink.visibility = View.VISIBLE
                }
            }

            link.addTextChangedListener {
                if (link.text.toString() != "") {
                    contentLink.text = link.text
                    contentVideo.visibility = View.VISIBLE
                    playVideo.visibility = View.VISIBLE
                    contentLink.visibility = View.VISIBLE
                } else {
                    contentLink.text = link.text
                    contentVideo.visibility = View.GONE
                    playVideo.visibility = View.GONE
                    contentLink.visibility = View.GONE
                }
            }

            fab.setOnClickListener {
                val intent = Intent()

                when {
                    TextUtils.isEmpty(content.text) -> {
                        setResult(0, intent)
                    }
                    extras == null -> {
                        intent.putExtra("postContent", content.text.toString())
                        intent.putExtra("postVideo", link.text.toString())
                        setResult(1, intent)
                    }
                    extras.getBoolean("edit") -> {
                        intent.putExtra("postId", extras.getLong("postId").toInt())
                        intent.putExtra("postContent", content.text.toString())
                        intent.putExtra("postVideo", link.text.toString())
                        setResult(2, intent)
                    }
                }
                finish()
            }
        }
    }
}