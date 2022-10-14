package com.denicks21.onboarding

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import android.widget.LinearLayout
import android.widget.TextView
import android.content.SharedPreferences
import android.os.Bundle
import android.content.Intent
import android.text.Html
import android.view.View
import android.widget.Button
import androidx.viewpager.widget.ViewPager.OnPageChangeListener

class MainActivity : AppCompatActivity() {
    var mSLideViewPager: ViewPager? = null
    var mDotLayout: LinearLayout? = null
    lateinit var backbtn: Button
    lateinit var nextbtn: Button
    lateinit var skipbtn: Button
    lateinit var dots: Array<TextView?>
    var viewPagerAdapter: ViewPagerAdapter? = null
    lateinit var firstTime: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        backbtn = findViewById(R.id.backbtn)
        nextbtn = findViewById(R.id.nextbtn)
        skipbtn = findViewById(R.id.skipButton)

        // Check if the app was opened for the first time
        firstTime = getSharedPreferences("firstTimeStart", MODE_PRIVATE)
        val isFirstTime = firstTime.getBoolean("firstTime", true)
        if (isFirstTime) {
            // If the app has never been opened then
            val editor = firstTime.edit()
            editor.putBoolean("firstTime", false)
            editor.apply()
            backbtn.setOnClickListener { v: View? ->
                if (getitem(0) > 0) {
                    mSLideViewPager!!.setCurrentItem(getitem(-1), true)
                }
            }

            nextbtn.setOnClickListener {
                if (getitem(0) < 8) mSLideViewPager!!.setCurrentItem(getitem(1), true) else {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
            }

            skipbtn.setOnClickListener {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }

            mSLideViewPager = findViewById(R.id.slideViewPager)
            mDotLayout = findViewById(R.id.indicator_layout)
            viewPagerAdapter = ViewPagerAdapter(this)
            mSLideViewPager!!.setAdapter(viewPagerAdapter)
            setUpindicator(0)
            mSLideViewPager!!.addOnPageChangeListener(viewListener)
        } else {
            // If the app has been opened then go to "HomeActivity"
            startActivity(Intent(applicationContext, HomeActivity::class.java))
            finish()
        }
    }

    fun setUpindicator(position: Int) {
        dots = arrayOfNulls(9)
        mDotLayout!!.removeAllViews()
        for (i in dots.indices) {
            dots[i] = TextView(this)
            dots[i]!!.text = Html.fromHtml("&#8226")
            dots[i]!!.textSize = 35f
            dots[i]!!
                .setTextColor(resources.getColor(R.color.white, applicationContext.theme))
            mDotLayout!!.addView(dots[i])
        }
        dots[position]!!
            .setTextColor(resources.getColor(R.color.primary, applicationContext.theme))
    }

    var viewListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            setUpindicator(position)
            if (position > 0) {
                backbtn.visibility = View.VISIBLE
            } else {
                backbtn.visibility = View.INVISIBLE
            }
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }

    private fun getitem(i: Int): Int {
        return mSLideViewPager!!.currentItem + i
    }
}