package com.david.maldonado.focusapp

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.Settings
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FocusModeActivity : AppCompatActivity() {
    private var timer: CountDownTimer? = null
    private var timeLeftInMillis = 1800000L // 30 minutos en milisegundos
    private var timerRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_focus_mode)

        val focusTimeTextView = findViewById<TextView>(R.id.focusTimeTextView)
        val startFocusButton = findViewById<Button>(R.id.startFocusButton)
        val stopFocusButton = findViewById<Button>(R.id.stopFocusButton)
        val focusProgressBar = findViewById<ProgressBar>(R.id.focusProgressBar)
        val motivationTextView = findViewById<TextView>(R.id.motivationTextView)

        // Configurar la barra circular y texto inicial
        focusProgressBar.max = (timeLeftInMillis / 1000).toInt() // Máximo de progreso
        focusProgressBar.progress = focusProgressBar.max        // Progreso inicial completo
        updateFocusTimeText(focusTimeTextView)                  // Actualiza el texto del temporizador
        updateMotivationalText(motivationTextView)

        startFocusButton.setOnClickListener {
            if (timerRunning) {
                pauseFocusTimer()
            } else {
                startFocusTimer(focusTimeTextView, focusProgressBar, motivationTextView)
                enableDoNotDisturb()
            }
        }

        stopFocusButton.setOnClickListener {
            resetFocusTimer(focusTimeTextView, focusProgressBar)
            disableDoNotDisturb()
        }

        updateFocusTimeText(focusTimeTextView)
        updateMotivationalText(motivationTextView)
    }

    private fun startFocusTimer(timeTextView: TextView, progressBar: ProgressBar, motivationalTextView: TextView) {
        progressBar.max = (timeLeftInMillis / 1000).toInt()
        timer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateFocusTimeText(timeTextView)
                progressBar.progress = (timeLeftInMillis / 1000).toInt()
            }

            override fun onFinish() {
                timerRunning = false
                updateMotivationalText(motivationalTextView)
                disableDoNotDisturb()
            }
        }.start()

        timerRunning = true
    }

    private fun pauseFocusTimer() {
        timer?.cancel()
        timerRunning = false
    }

    private fun resetFocusTimer(timeTextView: TextView, progressBar: ProgressBar) {
        timer?.cancel()
        timeLeftInMillis = 1800000L // Volver a los 30 minutos
        updateFocusTimeText(timeTextView)
        progressBar.progress = progressBar.max
        timerRunning = false
    }

    private fun updateFocusTimeText(timeTextView: TextView) {
        val minutes = (timeLeftInMillis / 1000) / 60
        val seconds = (timeLeftInMillis / 1000) % 60
        val timeFormatted = String.format("%02d:%02d", minutes, seconds)
        timeTextView.text = timeFormatted
    }

    private fun updateMotivationalText(motivationalTextView: TextView) {
        val motivationalQuotes = listOf(
            "¡Mantente enfocado y haz que suceda!",
            "¡Cada minuto cuenta!",
            "¡Estás en camino al éxito!",
            "Los pequeños pasos llevan a grandes resultados."
        )
        motivationalTextView.text = motivationalQuotes.random()
    }

    private fun enableDoNotDisturb() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (!notificationManager.isNotificationPolicyAccessGranted) {
            val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
            startActivity(intent)
        } else {
            notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_NONE)
        }
    }

    private fun disableDoNotDisturb() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (notificationManager.isNotificationPolicyAccessGranted) {
            notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL)
        }
    }
}
