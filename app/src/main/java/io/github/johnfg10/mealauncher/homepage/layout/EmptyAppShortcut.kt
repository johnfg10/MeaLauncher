package io.github.johnfg10.mealauncher.homepage.layout

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import io.github.johnfg10.mealauncher.R

class EmptyAppShortcut(ctx: Context, attrs: AttributeSet?, defStyle: Int) : RelativeLayout(ctx, attrs, defStyle) {

    constructor(ctx: Context, attrs: AttributeSet?) : this(ctx, attrs,0)

    constructor(ctx: Context) : this(ctx, null)

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.app_shortcut_empty, this)
    }
}