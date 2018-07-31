package io.github.johnfg10.mealauncher.homepage.layout

import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import io.github.johnfg10.mealauncher.R
import io.github.johnfg10.mealauncher.appinfo.AppInfo
import kotlinx.android.synthetic.main.app_shortcut.view.*
import android.view.LayoutInflater

class AppShortcut(ctx: Context, attrs: AttributeSet?, defStyle: Int) : RelativeLayout(ctx, attrs, defStyle), View.OnClickListener {

    var imageView: ImageView?
    var textView: TextView?


    init {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.app_shortcut, this)
        imageView = findViewById(R.id.imgView)
        textView = findViewById(R.id.txtView)

/*
        this.imageView.setImageDrawable(icon)
        this.textView.text = title
*/

        this.setOnClickListener(this)
    }

    var icon: Drawable? = null
        set(value) {
            if (this.imageView != null)
                this.imageView!!.setImageDrawable(value)
            field = value
        }

    var title: String = ""
        set(value) {
            if (this.textView != null)
                this.textView!!.text = value
            field = value
        }

    lateinit var intent: Intent

    constructor(ctx: Context, attrs: AttributeSet?) : this(ctx, attrs,0)

    constructor(ctx: Context) : this(ctx, null)

    constructor(ctx: Context, icon: Drawable, title: String, intent: Intent) : this(ctx){
        this.icon = icon
        this.title = title
        this.intent = intent
    }

    constructor(ctx: Context, appInfo: AppInfo) : this(ctx, appInfo.icon, appInfo.title, ctx.packageManager.getLaunchIntentForPackage(appInfo.appPackage))

    constructor(ctx: Context, appInfo: ResolveInfo) : this(ctx, appInfo.loadIcon(ctx.packageManager), appInfo.loadLabel(ctx.packageManager).toString(), ctx.packageManager.getLaunchIntentForPackage(appInfo.resolvePackageName))

    override fun onClick(v: View?) {
        context.startActivity(intent)
    }
}