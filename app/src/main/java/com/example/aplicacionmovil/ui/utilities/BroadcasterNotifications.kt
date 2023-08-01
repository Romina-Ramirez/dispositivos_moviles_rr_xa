package com.example.aplicacionmovil.ui.utilities

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.aplicacionmovil.R
import com.example.aplicacionmovil.ui.activities.NotificationActivity
import com.example.aplicacionmovil.ui.activities.SecondActivity

class BroadcasterNotifications : BroadcastReceiver()  {

    val CHANNEL: String = "Notificaciones"

    override fun onReceive(context: Context, intent: Intent) {
        val myIntent = Intent(context, SecondActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val myPendingIntent = PendingIntent.getActivity(context, 0, myIntent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

        val noti = NotificationCompat.Builder(context, CHANNEL)
        noti.setContentTitle("Broadcaster notificacion")
        noti.setContentText("Tienes una notificación programada.")
        noti.setSmallIcon(R.drawable.home_icon)
        noti.setPriority(NotificationCompat.PRIORITY_DEFAULT)

        noti.setStyle(
            NotificationCompat.BigTextStyle()
                .bigText("Esta es una notificación para recordar que tienes un recordatorio programado.")
        )
        noti.setContentIntent(myPendingIntent)

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager
        notificationManager.notify(1, noti.build())
    }

}