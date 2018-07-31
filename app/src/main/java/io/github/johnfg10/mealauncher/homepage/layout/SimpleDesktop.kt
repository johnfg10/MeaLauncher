package io.github.johnfg10.mealauncher.homepage.layout

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ListAdapter

import io.github.johnfg10.mealauncher.R
import io.github.johnfg10.mealauncher.appinfo.AppInfo
import io.github.johnfg10.mealauncher.appinfo.AppInfoViewHolder
import kotlinx.android.synthetic.main.fragment_simple_desktop.*

class SimpleDesktop() : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simple_desktop, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val i = Intent(Intent.ACTION_MAIN, null)
        i.addCategory(Intent.CATEGORY_LAUNCHER)
        val allApps = context?.packageManager?.queryIntentActivities(i, 0)
        val first = allApps?.first()

        val app = AppShortcut(this.activity!!, first!!.activityInfo.loadIcon(activity!!.packageManager), first.loadLabel(activity!!.packageManager).toString(), activity!!.packageManager.getLaunchIntentForPackage(first!!.activityInfo.packageName))
        val app2 = AppShortcut(this.activity!!, first!!.activityInfo.loadIcon(activity!!.packageManager), first.loadLabel(activity!!.packageManager).toString(), activity!!.packageManager.getLaunchIntentForPackage(first!!.activityInfo.packageName))
        val app3 = AppShortcut(this.activity!!, first!!.activityInfo.loadIcon(activity!!.packageManager), first.loadLabel(activity!!.packageManager).toString(), activity!!.packageManager.getLaunchIntentForPackage(first!!.activityInfo.packageName))
        val app4 = AppShortcut(this.activity!!, first!!.activityInfo.loadIcon(activity!!.packageManager), first.loadLabel(activity!!.packageManager).toString(), activity!!.packageManager.getLaunchIntentForPackage(first!!.activityInfo.packageName))
        val app5 = AppShortcut(this.activity!!, first!!.activityInfo.loadIcon(activity!!.packageManager), first.loadLabel(activity!!.packageManager).toString(), activity!!.packageManager.getLaunchIntentForPackage(first!!.activityInfo.packageName))
        desktopLayout.addAppShortcut(app, 1, 1)
        desktopLayout.addAppShortcut(app4, 2, 1)
        desktopLayout.addAppShortcut(app2, 1, 2)
        desktopLayout.addAppShortcut(app3, 1, 3)
        desktopLayout.addAppShortcut(app5, 4, 1)

    }
/*    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gridView.isVerticalScrollBarEnabled = false
        gridView.isHorizontalScrollBarEnabled = false

        Log.d("cols", gridView.numColumns.toString())


        adapter = AppShortcutAdapterN(this.activity!!)

        val i = Intent(Intent.ACTION_MAIN, null)
        i.addCategory(Intent.CATEGORY_LAUNCHER)

        val allApps = context?.packageManager?.queryIntentActivities(i, 0)
        if (allApps != null) {
            val first = allApps.first()
            //adapter.apps[adapter.apps.size] = AppInfo(first.loadLabel(context?.packageManager).toString(), first.activityInfo.packageName, first.activityInfo.loadIcon(context?.packageManager))

            //adapter.addApp(4, AppInfo(first.loadLabel(context?.packageManager).toString(), first.activityInfo.packageName, first.activityInfo.loadIcon(context?.packageManager)))
            adapter.addApp(2, 2, AppInfo(first.loadLabel(context?.packageManager).toString(), first.activityInfo.packageName, first.activityInfo.loadIcon(context?.packageManager)))
            adapter.addApp(1, 5, AppInfo(first.loadLabel(context?.packageManager).toString(), first.activityInfo.packageName, first.activityInfo.loadIcon(context?.packageManager)))
        }
//        adapter.apps.add(AppInfo("cal", "com.google.android.apps.calculator", context!!.packageManager.getApplicationIcon("com.google.android.apps.calculator")))
        gridView.adapter = adapter

    }*/
}
