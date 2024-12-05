package com.david.maldonado.focusapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class TasksAdapter(private val appUsageList: List<AppUsage>) : RecyclerView.Adapter<TasksAdapter.AppUsageViewHolder>() {

    // Crea las vistas para cada elemento
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppUsageViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_app_usage, parent, false)
        return AppUsageViewHolder(itemView)
    }

    // Asigna los valores a las vistas
    override fun onBindViewHolder(holder: AppUsageViewHolder, position: Int) {
        val appUsage = appUsageList[position]
        holder.appNameTextView.text = appUsage.appName
        holder.timeUsedTextView.text = appUsage.timeUsed
        holder.categoryTextView.text = appUsage.category
        holder.appIconImageView.setImageResource(appUsage.appIcon)
    }

    // Número de elementos en la lista
    override fun getItemCount(): Int = appUsageList.size

    // ViewHolder que maneja las vistas para cada elemento
    class AppUsageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val appNameTextView: TextView = itemView.findViewById(R.id.appNameTextView)
        val timeUsedTextView: TextView = itemView.findViewById(R.id.timeUsedTextView)
        val categoryTextView: TextView = itemView.findViewById(R.id.categoryTextView)
        val appIconImageView: ImageView = itemView.findViewById(R.id.appIconImageView)
    }
}
