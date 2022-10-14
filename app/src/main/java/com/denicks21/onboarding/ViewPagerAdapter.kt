package com.denicks21.onboarding

import android.content.Context
import androidx.viewpager.widget.PagerAdapter
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.LinearLayout
import android.widget.ScrollView

class ViewPagerAdapter(var context: Context) : PagerAdapter() {
    var images = intArrayOf(
        R.drawable.work_in_progress,
        R.drawable.work_in_progress,
        R.drawable.work_in_progress,
        R.drawable.work_in_progress,
        R.drawable.work_in_progress,
        R.drawable.work_in_progress,
        R.drawable.work_in_progress,
        R.drawable.work_in_progress,
        R.drawable.work_in_progress
    )
    var headings = intArrayOf(
        R.string.title_one,
        R.string.title_two,
        R.string.title_three,
        R.string.title_four,
        R.string.title_five,
        R.string.title_six,
        R.string.title_seven,
        R.string.title_eight,
        R.string.title_nine
    )
    var description = intArrayOf(
        R.string.desc_one,
        R.string.desc_two,
        R.string.desc_three,
        R.string.desc_four,
        R.string.desc_five,
        R.string.desc_six,
        R.string.desc_seven,
        R.string.desc_eight,
        R.string.desc_nine
    )

    override fun getCount(): Int {
        return headings.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.activity_layout, container, false)
        val slidetitleimage = view.findViewById<ImageView>(R.id.titleImage)
        val slideHeading = view.findViewById<TextView>(R.id.texttitle)
        val slideDesciption = view.findViewById<TextView>(R.id.textdeccription)
        slidetitleimage.setImageResource(images[position])
        slideHeading.setText(headings[position])
        slideDesciption.setText(description[position])
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ScrollView)
    }
}