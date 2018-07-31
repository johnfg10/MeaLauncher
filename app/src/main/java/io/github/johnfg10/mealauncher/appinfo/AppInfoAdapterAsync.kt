package io.github.johnfg10.mealauncher.appinfo

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.support.annotation.UiThread
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.github.johnfg10.mealauncher.R
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.timerTask

/**
 * AppInfoAdapterAsync makes extensive use of AsyncTask system to run alot of work on a background worker thread this reduces load and makes UI more responsive
 */
class AppInfoAdapterAsync(var apps: MutableList<AppInfo>) : RecyclerView.Adapter<AppInfoViewHolder>() {

    private val LOG_TAG = this::class.java.name

    /**
     * the amount of time the app launches should be saved in millieseconds
     */
    private val SAVE_TIMEOUT = TimeUnit.MILLISECONDS.convert(30, TimeUnit.MINUTES)

    /**
     * the delay between registering and the first save in miliseconds
     */
    private val SAVE_DELAY = TimeUnit.MILLISECONDS.convert(30, TimeUnit.MINUTES)

    constructor(context: Context) : this(mutableListOf()) {
        GenerateAppList(context.packageManager){
            SortAppInfoList{
                apps = it.toMutableList()
                this.notifyDataSetChanged()
            }.execute(*it.toTypedArray())
        }.execute()

        //used for the saving system not yet implemented
/*        Timer().schedule(timerTask {
            SaveAppList().execute(*apps.toTypedArray())
        }, SAVE_DELAY, SAVE_TIMEOUT)*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return AppInfoViewHolder(view, {return@AppInfoViewHolder this.apps}){ appInfo, pos -> apps[pos] = appInfo }
    }

    override fun getItemCount(): Int {return apps.size}

    override fun onBindViewHolder(holder: AppInfoViewHolder, position: Int) {
        val app = apps[position]
        holder.textView.text = app.title
        holder.imageView.setImageDrawable(app.icon)
        SortAppInfoList{
            apps = it.toMutableList()
        }.execute(*apps.toTypedArray())
    }

    fun getAppPositonStartsWith(startWith: String, callback: (Int)-> Unit){
        FindAppThatStartsWith(apps){
            //it will return -1 if not found and the positon otherwise
            callback.invoke(it)
        }.execute(startWith)
    }

    /**
     * Will at some point call handler and return the sorted AppInfo list
     */
    private class SortAppInfoList(val handler: (List<AppInfo>) -> Unit) : AsyncTask<AppInfo, Void, List<AppInfo>>() {
        override fun doInBackground(vararg params: AppInfo): List<AppInfo> {
            params.sortWith(Comparator { p0, p1 ->
                return@Comparator p0.title.compareTo(p1.title)
            })

            return Arrays.asList(*params)
        }

        override fun onPostExecute(result: List<AppInfo>) {
            handler.invoke(result)
        }
    }

    /**
     * will at some point call handler and return the new AppInfo list
     */
    private class GenerateAppList(val packageManger: PackageManager, val handler: (List<AppInfo>) -> Unit): AsyncTask<Void, Void, List<AppInfo>>() {
        override fun doInBackground(vararg params: Void?): List<AppInfo> {

            val i = Intent(Intent.ACTION_MAIN, null)
            i.addCategory(Intent.CATEGORY_LAUNCHER)

            val appInfoList = mutableListOf<AppInfo>()

            val allApps = packageManger.queryIntentActivities(i, 0)
            for (ri in allApps) {
                val app = AppInfo(ri.loadLabel(packageManger).toString(), ri.activityInfo.packageName, ri.activityInfo.loadIcon(packageManger))
                appInfoList.add(app)
            }

            return appInfoList
        }

        override fun onPostExecute(result: List<AppInfo>) {
            handler.invoke(result)
        }
    }

    /**
     * will save all AppInfo's to a database
     * TODO implement save functionality
     */
    public class SaveAppList(): AsyncTask<AppInfo, Int, Unit>() {
        override fun doInBackground(vararg params: AppInfo?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProgressUpdate(vararg values: Int?) {
            TODO("not implemented")
        }
    }

    private class FindAppThatStartsWith(val apps: List<AppInfo>, val callback: (Int) -> Unit): AsyncTask<String, Int, Int>() {
        override fun doInBackground(vararg params: String) : Int {
            val string = params.firstOrNull() ?: return -1
            apps.forEachIndexed { index, appInfo ->
                kotlin.run {
                    if (appInfo.title.startsWith(string, true)){
                        return index
                    }
                }
            }

            return -1
        }
        override fun onPostExecute(result: Int) {
            callback.invoke(result)
        }
    }
}

class AppInfoViewHolder(view: View, val handler: () -> List<AppInfo>, val updateHandler: (AppInfo, Int) -> Unit) : RecyclerView.ViewHolder(view), View.OnClickListener {

    val textView = view.findViewById<TextView>(R.id.textView)
    val imageView = view.findViewById<ImageView>(R.id.imageView)

    init {
        view.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val appInfo = handler.invoke()[adapterPosition]
        val ctx = v.context
        ctx.startActivity(ctx.packageManager.getLaunchIntentForPackage(appInfo.appPackage))
        appInfo.launchCount++
        updateHandler.invoke(appInfo, adapterPosition)
    }

}