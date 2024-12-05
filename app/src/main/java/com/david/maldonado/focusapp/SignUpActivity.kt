package com.david.maldonado.focusapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Obtener referencias a los campos de texto, el botón y el enlace
        val nameEditText = findViewById<EditText>(R.id.etName)  // Campo de nombre
        val emailEditText = findViewById<EditText>(R.id.etEmail)  // Campo de correo
        val passwordEditText = findViewById<EditText>(R.id.etPassword)  // Campo de contraseña
        val signUpButton = findViewById<Button>(R.id.signUpButton)  // Botón de registro
        val signInLink: TextView = findViewById(R.id.signInLink)  // Enlace para redirigir al login

        // Acción del botón de registro
        signUpButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Verificación de que los campos no estén vacíos
            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {

                // Verificar que el correo contenga "@"
                if (!email.contains("@")) {
                    Toast.makeText(this, "Por favor, ingresa un correo válido", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Verificar que la contraseña tenga al menos 8 caracteres
                if (password.length < 8) {
                    Toast.makeText(this, "La contraseña debe tener al menos 8 caracteres", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Si todo es correcto, mostrar un mensaje de éxito
                Toast.makeText(this, "Registro exitoso para $name", Toast.LENGTH_SHORT).show()

                // Crear el Intent para redirigir al inicio de sesión
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)  // Inicia la actividad de inicio de sesión
                finish()  // Finaliza la actividad actual
            } else {
                // Mostrar un mensaje de error si algún campo está vacío
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        // Acción del enlace de iniciar sesión
        signInLink.setOnClickListener {
            // Crear el Intent para abrir la actividad de inicio de sesión
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)  // Inicia la actividad de inicio de sesión
        }
    }
}
