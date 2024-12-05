package com.david.maldonado.focusapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewTasksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_tasks)

        // Lista de aplicaciones más usadas (ejemplo estático)
        val appUsageList = listOf(
            AppUsage("Instagram", "3 horas 10 minutos", "Redes sociales", R.drawable.ic_instagram),
            AppUsage("Facebook", "2 horas 15 minutos", "Redes sociales", R.drawable.ic_facebook),
            AppUsage("TikTok", "1 hora 45 minutos", "Redes sociales", R.drawable.ic_tiktok),
            AppUsage("Snapchat", "1 hora 30 minutos", "Redes sociales", R.drawable.ic_snapchat),
            AppUsage("YouTube", "5 horas 30 minutos", "Entretenimiento", R.drawable.ic_youtube),
            AppUsage("WhatsApp", "2 horas 5 minutos", "Comunicación", R.drawable.ic_whatsapp),
            AppUsage("Spotify", "4 horas", "Música", R.drawable.ic_spotify),
            AppUsage("Threads", "1 hora", "Redes sociales", R.drawable.ic_threads)
        )

        // Configuración del RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewAppUsage)
        recyclerView.layoutManager = LinearLayoutManager(this) // Alineación vertical de las aplicaciones
        recyclerView.adapter = TasksAdapter(appUsageList) // Establecemos el adaptador para mostrar las aplicaciones
    }
}
