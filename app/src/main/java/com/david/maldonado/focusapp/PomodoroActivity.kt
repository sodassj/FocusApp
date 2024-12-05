package com.david.maldonado.focusapp

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PomodoroActivity : AppCompatActivity() {

    companion object {
        private const val TIMER_INITIAL_TIME = 1500000L // 25 minutes in milliseconds
        private const val TIMER_INTERVAL = 1000L // 1 second
    }

    private lateinit var timer: CountDownTimer
    private var timeLeftInMillis = TIMER_INITIAL_TIME
    private var timerRunning = false
    private var sessionCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pomodoro)

        val timeTextView = findViewById<TextView>(R.id.timeTextView)
        val startPauseButton = findViewById<Button>(R.id.startPauseButton)
        val resetButton = findViewById<Button>(R.id.resetButton)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val motivationalTextView = findViewById<TextView>(R.id.motivationalTextView)

        startPauseButton.setOnClickListener {
            if (timerRunning) pauseTimer(startPauseButton)
            else startTimer(timeTextView, progressBar, motivationalTextView, startPauseButton)
        }

        resetButton.setOnClickListener {
            resetTimer(timeTextView, progressBar, startPauseButton)
        }

        updateTimerText(timeTextView)
        updateMotivationalText(motivationalTextView)
    }

    private fun startTimer(
        timeTextView: TextView,
        progressBar: ProgressBar,
        motivationalTextView: TextView,
        startPauseButton: Button
    ) {
        progressBar.max = (TIMER_INITIAL_TIME / TIMER_INTERVAL).toInt()
        timer = object : CountDownTimer(timeLeftInMillis, TIMER_INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateTimerText(timeTextView)
                progressBar.progress = (timeLeftInMillis / TIMER_INTERVAL).toInt()
            }

            override fun onFinish() {
                sessionCount++
                updateMotivationalText(motivationalTextView)
                timerRunning = false
                startPauseButton.text = "Start"
            }
        }.start()

        timerRunning = true
        startPauseButton.text = "Pause"
    }

    private fun pauseTimer(startPauseButton: Button) {
        timer.cancel()
        timerRunning = false
        startPauseButton.text = "Start"
    }

    private fun resetTimer(
        timeTextView: TextView,
        progressBar: ProgressBar,
        startPauseButton: Button
    ) {
        timer.cancel()
        timeLeftInMillis = TIMER_INITIAL_TIME
        updateTimerText(timeTextView)
        progressBar.progress = progressBar.max
        timerRunning = false
        startPauseButton.text = "Start"
    }

    private fun updateTimerText(timeTextView: TextView) {
        val minutes = (timeLeftInMillis / TIMER_INTERVAL) / 60
        val seconds = (timeLeftInMillis / TIMER_INTERVAL) % 60
        timeTextView.text = String.format("%02d:%02d", minutes, seconds)
    }

    private fun updateMotivationalText(motivationalTextView: TextView) {
        val motivationalQuotes = listOf(
            "Mantente concentrado, mantente fuerte!",
            "One step at a time.",
            "Keep going, you're doing great!",
            "Focus on progress, not perfection."
        )
        motivationalTextView.text = motivationalQuotes.getOrNull(sessionCount % motivationalQuotes.size)
            ?: "Keep it up!"
    }
}
