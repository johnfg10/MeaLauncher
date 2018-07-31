package io.github.johnfg10.mealauncher

import android.content.Context
import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import io.github.johnfg10.mealauncher.R.id.recycleView
import io.github.johnfg10.mealauncher.appinfo.AppInfoAdapterAsync
import kotlinx.android.synthetic.main.activity_app_drawer.*
import kotlinx.android.synthetic.main.activity_app_drawer.view.*
import kotlin.math.roundToInt
import android.util.DisplayMetrics
import android.widget.LinearLayout
import io.github.johnfg10.mealauncher.charrecycler.CharRecyclerAdapterAsync
import io.github.johnfg10.mealauncher.helpers.CenterLayoutManager


class AppDrawer : AppCompatActivity() {

    private val LOG_TAG = this::class.java.name

    lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: AppInfoAdapterAsync

    lateinit var charRecyclerVieww: RecyclerView
    private lateinit var charViewAdapter: CharRecyclerAdapterAsync

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_drawer)

        recyclerView = recycleView
        viewAdapter = AppInfoAdapterAsync(this)
        recyclerView.adapter = viewAdapter
        recyclerView.layoutManager = CenterLayoutManager(this)


        charRecyclerVieww = charRecyclerView
        charViewAdapter = CharRecyclerAdapterAsync(){
            viewAdapter.getAppPositonStartsWith(it){
                if (it != -1)
                    recyclerView.smoothScrollToPosition(it)


            }
        }
        charRecyclerVieww.adapter = charViewAdapter
    }

}
