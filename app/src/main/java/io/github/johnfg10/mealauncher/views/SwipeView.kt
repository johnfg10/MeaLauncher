package io.github.johnfg10.mealauncher.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.support.v4.view.GestureDetectorCompat
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView

class SwipeView(context: Context) : FrameLayout(context) {


    var gestureListener: GestureDetector.SimpleOnGestureListener? = null
    set(value) {
        gestureDetector = GestureDetectorCompat(context, gestureListener)
        field = value
    }

    lateinit var gestureDetector: GestureDetectorCompat

    init {

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }
}