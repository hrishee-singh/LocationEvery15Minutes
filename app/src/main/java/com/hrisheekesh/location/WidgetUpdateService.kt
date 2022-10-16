package com.hrisheekesh.location

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import com.google.android.gms.location.LocationServices

class WidgetUpdateService : JobIntentService() {

    override fun onHandleWork(intent: Intent) {
        val appWidgetId = intent.extras?.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID)
            ?: AppWidgetManager.INVALID_APPWIDGET_ID

        if (appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
            val locationProvider = LocationServices.getFusedLocationProviderClient(this)

        }
    }

    companion object {

        private const val JOB_ID = 1

        fun enqueueWork(context: Context, appWidgetId: Int) {
            val intent = Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            enqueueWork(context, WidgetUpdateService::class.java, JOB_ID, intent)
        }
    }
}

class LocationWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        appWidgetIds.forEach { appWidgetId ->
            WidgetUpdateService.enqueueWork(context, appWidgetId)
        }
    }
}