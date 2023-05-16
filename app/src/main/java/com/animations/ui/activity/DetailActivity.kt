package com.animations.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.animations.R
import com.animations.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class DetailActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityDetailBinding

    companion object{
        const val ARG_URL = "ARG_URL"
        fun getIntent(context: Context,url:String): Intent {
            return Intent(context,DetailActivity::class.java).apply {
                this.putExtra(ARG_URL,url)
            }
        }
    }

    private val mUrl by lazy {
        intent.getStringExtra(ARG_URL)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(binding.root.context)
            .load(mUrl)
            .into(binding.imageView)
    }
}