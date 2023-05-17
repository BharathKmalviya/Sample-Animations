package com.animations.ui

import android.app.ActivityOptions
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import com.animations.R
import com.animations.databinding.ActivityMainBinding
import com.animations.ui.activity.DetailActivity
import com.animations.ui.adapters.PagerAdapter
import kotlin.math.abs
import kotlin.math.max


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mAdapter by lazy {
        PagerAdapter(ArrayList()) {
            startActivity(
                DetailActivity.getIntent(this, it.second),
                ActivityOptions.makeSceneTransitionAnimation(
                    this,
                    android.util.Pair(it.first.imageView, it.first.imageView.transitionName),
                    android.util.Pair(it.first.view, it.first.view.transitionName),
                    android.util.Pair(it.first.tvTitle, it.first.tvTitle.transitionName),
                    android.util.Pair(it.first.tvSubTitle, it.first.tvSubTitle.transitionName),
                    android.util.Pair(it.first.tvDesc, it.first.tvDesc.transitionName),
                ).toBundle()
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        val list = ArrayList<String>()
        list.add("https://i.imgur.com/CzXTtJV.jpg")
        list.add("https://i.imgur.com/OB0y6MR.jpg")
        list.add("https://farm2.staticflickr.com/1533/26541536141_41abe98db3_z_d.jpg")
        list.add("https://farm4.staticflickr.com/3075/3168662394_7d7103de7d_z_d.jpg")
        list.add("https://farm9.staticflickr.com/8505/8441256181_4e98d8bff5_z_d.jpg")
        list.add("https://i.imgur.com/OnwEDW3.jpg")
        list.add("https://farm3.staticflickr.com/2220/1572613671_7311098b76_z_d.jpg")
        list.add("https://farm7.staticflickr.com/6089/6115759179_86316c08ff_z_d.jpg")
        list.add("https://farm2.staticflickr.com/1090/4595137268_0e3f2b9aa7_z_d.jpg")
        list.add("https://farm4.staticflickr.com/3224/3081748027_0ee3d59fea_z_d.jpg")
        list.add("https://farm8.staticflickr.com/7377/9359257263_81b080a039_z_d.jpg")
        list.add("https://farm9.staticflickr.com/8295/8007075227_dc958c1fe6_z_d.jpg")
        list.add("https://farm2.staticflickr.com/1449/24800673529_64272a66ec_z_d.jpg")
        list.add("https://farm4.staticflickr.com/3752/9684880330_9b4698f7cb_z_d.jpg")

        with(binding.viewPager) {
            setPageTransformer { page, position ->
                clipToPadding = false
                clipChildren = false
                offscreenPageLimit = 3
                setPageTransformer { page, position ->
                    val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
                    val offsetPx = resources.getDimensionPixelOffset(R.dimen.offset)
                    val viewPager = page.parent.parent as ViewPager2
                    val offset = position * -(2 * offsetPx + pageMarginPx)
                    if (viewPager.orientation == ORIENTATION_HORIZONTAL) {
                        if (ViewCompat.getLayoutDirection(viewPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                            page.translationX = -offset
                        } else {
                            page.translationX = offset
                        }
                    } else {
                        page.translationY = offset
                    }


                    val scaleFactor = max(0.9f, 1 - abs(position))
                    page.scaleX = scaleFactor
                    page.scaleY = scaleFactor
                }
            }

            adapter = mAdapter
        }
        mAdapter.updateData(list)
    }
}




