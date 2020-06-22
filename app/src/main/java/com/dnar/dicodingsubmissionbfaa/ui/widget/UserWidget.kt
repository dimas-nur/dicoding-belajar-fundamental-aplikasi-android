package com.dnar.dicodingsubmissionbfaa.ui.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.core.net.toUri
import com.dnar.dicodingsubmissionbfaa.R
import com.dnar.dicodingsubmissionbfaa.ui.main.MainActivity

class UserWidget : AppWidgetProvider() {

    companion object {

        fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            // Intent to UserWidgetService for load data from database
            val intent = Intent(context, UserWidgetService::class.java).apply {
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                data = toUri(Intent.URI_INTENT_SCHEME).toUri()
            }

            // Intent to app when onTittleClickListener
            val tittleIntent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val tittlePendingIntent = PendingIntent.getActivity(context, 0, tittleIntent, 0)

            // Set parent view
            val views = RemoteViews(context.packageName, R.layout.user_widget).apply {
                setRemoteAdapter(R.id.userWidget_stack_view, intent)
                setEmptyView(R.id.userWidget_stack_view, R.id.userWidget_empty_view)

                setTextViewText(
                    R.id.userWidget_tittle,
                    context.getString(R.string.home_favorite)
                )
                setTextViewText(
                    R.id.userWidget_tv_tittle,
                    context.getString(R.string.placeholder_error_tittle)
                )
                setTextViewText(
                    R.id.userWidget_tv_message,
                    context.getString(R.string.placeholder_not_found_favorite_message)
                )
                setOnClickPendingIntent(
                    R.id.userWidget_tittle,
                    tittlePendingIntent
                )
            }

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }

        fun sendRefreshBroadcast(context: Context) {
            val intent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE).apply {
                component = ComponentName(context, UserWidget::class.java)
            }
            context.sendBroadcast(intent)
        }
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            if (it.action == AppWidgetManager.ACTION_APPWIDGET_UPDATE) {
                // refresh all your widgets
                val component = context?.let { context ->
                    ComponentName(
                        context,
                        UserWidget::class.java
                    )
                }
                AppWidgetManager.getInstance(context).apply {
                    notifyAppWidgetViewDataChanged(
                        getAppWidgetIds(component),
                        R.id.userWidget_stack_view
                    )
                }
            }
        }
        super.onReceive(context, intent)
    }
}