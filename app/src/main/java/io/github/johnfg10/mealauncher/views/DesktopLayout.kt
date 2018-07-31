package io.github.johnfg10.mealauncher.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.GridLayout
import io.github.johnfg10.mealauncher.homepage.layout.AppShortcut

class DesktopLayout(context: Context, attrb: AttributeSet? = null, defStyleAttr: Int = 0) : GridLayout(context, attrb, defStyleAttr) {

    val LOG_TAG = this::class.java.name
    val MARGIN_SIZE = 20

    val layoutMap = mutableMapOf<Int, MutableMap<Int, Int>>()
    var lastUsedId = 0


    constructor(context: Context, attrb: AttributeSet) : this(context, attrb, 0)

    constructor(context: Context) : this(context,null)

    init {
        rowCount = 7
        columnCount = 4

        for (row in 1 until rowCount){
            for (col in 1 until columnCount){
                addEmptyCell(col, row)
            }
        }
    }

    fun addEmptyCell(xPos: Int, yPos: Int){
        val layoutParams = LayoutParams()

        layoutParams.setMargins(MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE)
        this.addVieww(io.github.johnfg10.mealauncher.homepage.layout.EmptyAppShortcut(context), layoutParams, xPos, yPos)
    }

    fun addAppShortcut(app: AppShortcut, xPos: Int, yPos: Int){
/*        Log.d(LOG_TAG, "column count: ${this.columnCount}, ${this.rowCount}")
        if (xPos > this.columnCount)
            throw RuntimeException("xPos is larger then the column count")
        if (yPos > this.rowCount)
            throw RuntimeException("yPos is larger then the row count")*/
        //todo work out way to calcaulate if the view ill fit

        val layoutParams = LayoutParams()

        layoutParams.setMargins(MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE)

        this.addVieww(app, layoutParams, xPos, yPos)
    }

    fun addVieww(view: View, layoutParams: LayoutParams, xPos: Int, yPos: Int){
        layoutParams.rowSpec = GridLayout.spec(yPos, GridLayout.LEFT)
        layoutParams.columnSpec =  GridLayout.spec(xPos, GridLayout.LEFT)


        if (layoutMap.containsKey(xPos) && layoutMap[xPos]!!.containsKey(yPos) && layoutMap[xPos]!![yPos] != null){
            val id = layoutMap[xPos]!![yPos]
            this.removeViewAt(id!!)
        }

        lastUsedId += 1

        //layoutMap[xPos][yPos] = lastUsedId

        this.addView(view, lastUsedId, android.widget.GridLayout.LayoutParams(layoutParams))
    }
}