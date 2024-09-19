package com.example.fourgroundservice

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

enum class Actions {
    START, STOP
}

class RunningService : Service() {
    /**
     * onBind function is override from the service class
     * which is receive an intent and returns IBinder
     * that function is used to create something called bound service so what you can also do with the is you can have one active instance
     * and multiple components connects to that single instance and have done a stream for communication to communicate with that service
     * and also receive such events or callbacks is back from service if that is a use case of multiple apps need to connect to one single service
     *
     */
    override fun onBind(p0: Intent?): IBinder? {
        Log.d("RunningService", "onBind: is call")
        return null
    }

    /**
     * this triggered whenever another android component sends an intent to
     * this running service
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("RunningService", "onStartCommand: is call")
        when (intent?.action) {
            Actions.START.name -> start()
            Actions.STOP.name -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    @SuppressLint("ForegroundServiceType")
    private fun start() {
        val notification = NotificationCompat.Builder(this, "running_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Run is active")
            .setContentText("Elapsed time : 00:50")
            .build()
        startForeground(1, notification)
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("RunningService", "onCreate: is call")
    }

}

