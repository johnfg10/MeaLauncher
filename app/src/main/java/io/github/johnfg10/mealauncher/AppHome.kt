package io.github.johnfg10.mealauncher

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GestureDetectorCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import io.github.johnfg10.mealauncher.appinfo.AppInfo
import io.github.johnfg10.mealauncher.homepage.layout.DesktopAdapter
import io.github.johnfg10.mealauncher.homepage.layout.SimpleDesktop

import kotlinx.android.synthetic.main.activity_app_home.*
import kotlin.math.roundToInt

class AppHome : AppCompatActivity() {

    private lateinit var gestureDetector: GestureDetectorCompat

    private lateinit var viewPager: ViewPager

    val desktopAdapter = DesktopAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_home)

        this.viewPager = view_pager
        viewPager.adapter = desktopAdapter
        desktopAdapter.addNewDesktop(SimpleDesktop())

        gestureDetector = GestureDetectorCompat(this, GestureListener(this.applicationContext))
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        gestureDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }


    private class GestureListener(val ctx: Context) : GestureDetector.SimpleOnGestureListener() {
        private val LOG_TAG = this::class.java.name


        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            Log.d(LOG_TAG, velocityY.roundToInt().toString())

            if (velocityY <= -3000){
                val intent = Intent(ctx, AppDrawer::class.java)
                ctx.startActivity(intent)
            }

            return true
        }
    }
}
