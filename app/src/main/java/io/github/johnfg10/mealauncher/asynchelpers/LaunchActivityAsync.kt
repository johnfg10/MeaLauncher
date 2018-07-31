package io.github.johnfg10.mealauncher.asynchelpers

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.AsyncTask

class LaunchActivityAsync(@SuppressLint("StaticFieldLeak") val ctx: Context, val callback: () -> Unit) : AsyncTask<Intent, Void, Unit>() {
    override fun doInBackground(vararg params: Intent?) {
        ctx.startActivity(params[0])
    }

    override fun onPostExecute(result: Unit?) {
        callback.invoke()
    }
}