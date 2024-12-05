package com.david.maldonado.focusapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Obtener referencias a los campos de texto, el botón y el enlace
        val emailEditText = findViewById<EditText>(R.id.etEmail)  // Campo de correo
        val passwordEditText = findViewById<EditText>(R.id.etPassword)  // Campo de contraseña
        val loginButton = findViewById<Button>(R.id.loginButton)  // Botón de inicio de sesión
        val signUpLink: TextView = findViewById(R.id.signUpLink)  // Enlace para redirigir al registro

        // Acción del botón de inicio de sesión
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Verificación de que los campos no estén vacíos
            if (email.isNotEmpty() && password.isNotEmpty()) {

                // Verificación de formato de correo
                if (!email.contains("@")) {
                    Toast.makeText(this, "Por favor, ingresa un correo válido", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Verificación de la longitud de la contraseña
                if (password.length < 8) {
                    Toast.makeText(this, "La contraseña debe tener al menos 8 caracteres", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Si todo es correcto, mostrar un mensaje de éxito
                Toast.makeText(this, "Bienvenido, $email", Toast.LENGTH_SHORT).show()

                // Crear el Intent para abrir la actividad MainMenuActivity
                val intent = Intent(this, MainMenuActivity::class.java)
                startActivity(intent)  // Inicia la actividad del menú principal

                // Finaliza la actividad de inicio de sesión para que no se pueda volver a ella
                finish()
            } else {
                // Mostrar un mensaje de error si algún campo está vacío
                Toast.makeText(this, "Por favor, ingresa correo y contraseña", Toast.LENGTH_SHORT).show()
            }
        }

        // Acción del enlace de registro
        signUpLink.setOnClickListener {
            // Crear el Intent para abrir la actividad de registro
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)  // Inicia la actividad de registro
        }
    }
}
