package io.github.johnfg10.mealauncher.appinfo

import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import android.support.v4.content.ContextCompat

data class AppInfo(val title: String , val appPackage: String, val icon: Drawable, var launchCount: Int = 0) {
/*    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt())*/


}

data class AppInfoa(val title: String, val appPackage: String, val iconId: Int, var launchCount: Int){}