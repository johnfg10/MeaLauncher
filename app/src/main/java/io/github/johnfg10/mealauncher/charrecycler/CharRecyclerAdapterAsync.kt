package io.github.johnfg10.mealauncher.charrecycler

import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.github.johnfg10.mealauncher.R
import io.github.johnfg10.mealauncher.appinfo.AppInfo
import io.github.johnfg10.mealauncher.appinfo.AppInfoAdapterAsync
import io.github.johnfg10.mealauncher.appinfo.AppInfoViewHolder
import java.util.*

class CharRecyclerAdapterAsync(var stringList: MutableList<String>, val callback: (String) -> Unit) : RecyclerView.Adapter<CharViewHolder>() {

    constructor(callback: (String) -> Unit) : this(mutableListOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"), callback){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.char_layout, parent, false)
        return CharViewHolder(view, callback)
    }

    override fun getItemCount(): Int {
        return stringList.size
    }

    override fun onBindViewHolder(holder: CharViewHolder, position: Int) {
        holder.textView.text = stringList[position]
        SortAppInfoList {
            stringList = it.toMutableList()
        }.execute(*stringList.toTypedArray())
    }

    /**
     * Will at some point call handler and return the sorted AppInfo list
     */
    private class SortAppInfoList(val handler: (List<String>) -> Unit) : AsyncTask<String, Void, List<String>>() {
        override fun doInBackground(vararg params: String): List<String> {
            params.sortWith(Comparator { p0, p1 ->
                return@Comparator p0.compareTo(p1)
            })

            return Arrays.asList(*params)
        }

        override fun onPostExecute(result: List<String>) {
            handler.invoke(result)
        }
    }
}

class CharViewHolder(val view: View, val callback: (String) -> Unit) : RecyclerView.ViewHolder(view), View.OnClickListener{
    val textView = view.findViewById<TextView>(R.id.textView2)

    init {
        view.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        callback.invoke(textView.text.toString())
    }
}