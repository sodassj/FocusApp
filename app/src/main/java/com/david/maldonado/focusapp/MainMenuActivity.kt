package com.david.maldonado.focusapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        // Encontrar los botones en el layout
        val addTaskButton = findViewById<Button>(R.id.addTaskButton)
        val viewTasksButton = findViewById<Button>(R.id.viewTasksButton)
        val pomodoroButton = findViewById<Button>(R.id.pomodoroButton)
        val focusModeButton = findViewById<Button>(R.id.focusModeButton)

        // Enlazar el botón "Agregar Tarea"
        addTaskButton.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }

        // Enlazar el botón "Ver Tareas"
        viewTasksButton.setOnClickListener {
            val intent = Intent(this, ViewTasksActivity::class.java)
            startActivity(intent)
        }

        // Enlazar el botón "Pomodoro"
        pomodoroButton.setOnClickListener {
            val intent = Intent(this, PomodoroActivity::class.java)
            startActivity(intent)
        }

        // Enlazar el botón "Modo Focus"
        focusModeButton.setOnClickListener {
            val intent = Intent(this, FocusModeActivity::class.java)
            startActivity(intent)
        }
    }
}
