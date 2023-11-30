/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.data.sensors

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.ServiceInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import projet.mobile.kotlin.fitsv.R
import projet.mobile.kotlin.fitsv.data.source.local.StepCounterDao
import projet.mobile.kotlin.fitsv.domain.model.StepCounterModel
import javax.inject.Inject

/**
 * Class HardwareStepCounterSource
 * TODO Comment use case of class HardwareStepCounterSource
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
@HiltWorker
class HardwareStepCounterSource @AssistedInject constructor(
    private val stepCounterDao: StepCounterDao,
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters
): SensorEventListener, CoroutineWorker(context, workerParams) {

    init {
        Log.d("HardwareStepCounterSource", "Service created")
    }

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private val stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

    private var numberOfStep = 0
    private var baseStepNumber: Int? = null

    override fun onSensorChanged(event: SensorEvent?) {
        Log.d("HardwareStepCounterSource", "on sensor changed called")
        event?.also {
            if (baseStepNumber == null) {
                Log.d("HardwareStepCounterSource", "baseStepNumber Null")
                baseStepNumber = it.values[0].toInt()
            }
            numberOfStep += (it.values[0].toInt() - baseStepNumber!!)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return // Do nothing
    }

    override suspend fun doWork(): Result {
        withContext(Dispatchers.IO){

        }
        Log.d("HardwareStepCounterSource", "doWork called")
        if (stepCounterSensor == null) {
            Log.d("HardwareStepCounterSource", "stepCounterSensor null")
            return Result.failure()
        }
        Log.d("HardwareStepCounterSource", "stepCounterSensor not null")

//        stepCounterDao.insert(StepCounterModel(0, steps = numberOfStep))

        sensorManager.registerListener(this,
            stepCounterSensor,
            SensorManager.SENSOR_DELAY_NORMAL)
        setForeground(getForegroundInfo())

        update()
        return Result.success()
    }

    private suspend fun update() {
        Log.d("HardwareStepCounterSource", "update called")
        while (!isStopped) {
            delay(5000) // attente de 5s

            setForeground(createForegroundInfo())
        }
    }


    override suspend fun getForegroundInfo(): ForegroundInfo {
            Log.d("HardwareStepCounterSource", "getForegroundInfo called")
            return createForegroundInfo()
    }

    private fun createForegroundInfo(): ForegroundInfo {
        Log.d("HardwareStepCounterSource", "createForegroundInfo called")
        val title = "Steps"
        val channelId = "projet.mobile.kotlin.fitsv.STEP_CHANNEL_ID"


        var notificationBuilder: NotificationCompat.Builder =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createChanel(channelId, applicationContext)
                NotificationCompat.Builder(applicationContext, channelId)
            } else {
                NotificationCompat. Builder(applicationContext)
            }

        val notification = notificationBuilder
            .setContentTitle(title)
            .setTicker(title)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentText("$numberOfStep")
            .setOngoing(true)
            .setForegroundServiceBehavior(Notification.FOREGROUND_SERVICE_IMMEDIATE) // lance notif dés que build
            .build()

        return ForegroundInfo(SEPT_NOTIFICATION_CHANNEL,
            notification,
            ServiceInfo.FOREGROUND_SERVICE_TYPE_HEALTH)

    }

    private fun createChanel(chanelId: String, context: Context) {
        Log.d("HardwareStepCounterSource", "createChanel called")
        val name = context.getString(R.string.stepChanelName)
        val descriptionText = context.getString(R.string.stepChanelDesc)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(chanelId, name, importance).apply {
            description = descriptionText
        }
        notificationManager.createNotificationChannel(channel)
    }

    companion object {
        const val SEPT_NOTIFICATION_CHANNEL = 0
    }
}