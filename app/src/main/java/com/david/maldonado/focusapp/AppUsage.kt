package com.david.maldonado.focusapp

// Clase de datos que representa una aplicación y su tiempo de uso
data class AppUsage(
    val appName: String,      // Nombre de la aplicación
    val timeUsed: String,     // Tiempo usado en la aplicación (por ejemplo, "3 horas 15 minutos")
    val category: String,      // Categoría de la aplicación (por ejemplo, "Educación", "Comunicación")
    val appIcon: Int
)