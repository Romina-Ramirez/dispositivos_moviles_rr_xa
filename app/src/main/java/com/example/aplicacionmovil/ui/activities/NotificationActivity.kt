package com.example.aplicacionmovil.ui.activities

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat.from
import com.example.aplicacionmovil.R
import com.example.aplicacionmovil.databinding.ActivityNotificationBinding
import com.example.aplicacionmovil.ui.utilities.BroadcasterNotifications
import java.util.Calendar
import java.util.Date

class NotificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationBinding
    val CHANNEL: String = "Notificaciones"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createNotificationChannel()

        binding.notificacionBtn.setOnClickListener {
            sendNotificacion()
        }

        binding.notificacionProgramadaBtn.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hora = binding.timePicker.hour
            val minutes = binding.timePicker.minute
            Toast.makeText(
                this,
                "La notificación se activará a las $hora:$minutes",
                Toast.LENGTH_SHORT
            ).show()
            calendar.set(Calendar.HOUR, hora)
            calendar.set(Calendar.MINUTE, minutes)
            calendar.set(Calendar.SECOND, 0)

            sendNotificacionTimePicker(calendar.timeInMillis)
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Variedades"
            val descriptionText = "Notificaciones simples de variedades."
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotificacionTimePicker(time: Long) {
        val myIntent = Intent(applicationContext, BroadcasterNotifications::class.java)
        val myPendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            0,
            myIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, myPendingIntent)
    }

    @SuppressLint("MissingPermission")
    fun sendNotificacion() {
        val myIntent = Intent(this, SecondActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val myPendingIntent = PendingIntent.getActivity(
            this,
            0,
            myIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val noti = NotificationCompat.Builder(this, CHANNEL)
        noti.setContentTitle("Primera notificacion")
        noti.setContentText("Tienes una notificación.")
        noti.setSmallIcon(R.drawable.home_icon)
        noti.setPriority(NotificationCompat.PRIORITY_DEFAULT)

        noti.setStyle(
            NotificationCompat.BigTextStyle()
                .bigText("Esta es una notificación para recordar que estamos trabajando en Android.")
        )

        noti.setContentIntent(myPendingIntent)

        with(from(this)) {
            notify(1, noti.build())
        }
    }
}