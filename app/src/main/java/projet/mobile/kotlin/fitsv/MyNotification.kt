package projet.mobile.kotlin.fitsv

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class MyNotification(
    private var context: Context,
    private var title: String,
    private var msg: String) {
    private val chanelID: String = "FCM100"
    private val chanelName: String = "FCMMessage"
    private val notificationManager = context.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    private lateinit var notificationChanel : NotificationChannel
    private lateinit var notificationBuilder : NotificationCompat.Builder

    fun fireNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChanel = NotificationChannel(chanelID, chanelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChanel)
        }

        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)


        notificationBuilder = NotificationCompat.Builder(context, chanelID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .addAction(R.drawable.ic_stat_ic_notification, "Open Message", pendingIntent)
            .setContentTitle(title)
            .setContentText(msg)
            .setAutoCancel(true)
        notificationManager.notify(100, notificationBuilder.build())

    }

}
