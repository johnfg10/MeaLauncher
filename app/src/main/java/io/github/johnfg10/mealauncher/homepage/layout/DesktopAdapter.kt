package io.github.johnfg10.mealauncher.homepage.layout

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class DesktopAdapter(fragmentManager: FragmentManager, val desktops: MutableList<Fragment> = mutableListOf()) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return desktops[position]
    }

    override fun getCount(): Int {
        return desktops.size
    }

    fun addNewDesktop(fragment: Fragment, position: Int = -1){
        if (position > 0) desktops.add(position, fragment) else desktops.add(fragment)
        this.notifyDataSetChanged()
    }
}