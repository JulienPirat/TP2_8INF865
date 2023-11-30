package com.example.tp2_8inf865.data.workers

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay

@HiltWorker
class TempSensorWorker @AssistedInject constructor(
    @Assisted context : Context,
    @Assisted params : WorkerParameters,
): SensorEventListener, CoroutineWorker(context,params){

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE)
            as SensorManager

    private val AmbientTempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)

    private var Temp: Int? = null;

    override suspend fun doWork(): Result {
        /*
        if(Temp == null){
            return Result.failure()
        }*/
        try {
            sensorManager.registerListener(
                this,
                AmbientTempSensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )

            delay(5000)

            if(Temp == null){
                doWork()
            }

            var outputData = Data.Builder()
                .putInt("Temp", Temp!!)
                .build()

            return Result.success(outputData)
        } catch (e: Exception) {
            return Result.failure()
        }
    }
    override fun onSensorChanged(event: SensorEvent?) {
        event?.also {
            Temp = it.values[0].toInt()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

        //TODO("Not yet implemented")
    }
}