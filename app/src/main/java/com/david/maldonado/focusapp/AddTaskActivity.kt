package com.david.maldonado.focusapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val taskNameEditText = findViewById<EditText>(R.id.taskNameEditText)
        val taskDescriptionEditText = findViewById<EditText>(R.id.taskDescriptionEditText)
        val saveTaskButton = findViewById<Button>(R.id.saveTaskButton)

        saveTaskButton.setOnClickListener {
            val taskName = taskNameEditText.text.toString()
            val taskDescription = taskDescriptionEditText.text.toString()
            Toast.makeText(this, "Tarea '$taskName' guardada", Toast.LENGTH_SHORT).show()
        }
    }
}