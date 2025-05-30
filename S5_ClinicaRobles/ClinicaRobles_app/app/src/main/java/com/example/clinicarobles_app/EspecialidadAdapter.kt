package com.example.clinicarobles_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EspecialidadAdapter(private val lista: List<Especialidad>) :
    RecyclerView.Adapter<EspecialidadAdapter.EspecialidadViewHolder>() {

    class EspecialidadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.txtNombre)
        val doctor: TextView = itemView.findViewById(R.id.txtDoctor)
        val horario: TextView = itemView.findViewById(R.id.txtHorario)
        val imagen: ImageView = itemView.findViewById(R.id.imgEspecialidad)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EspecialidadViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_especialidad, parent, false)
        return EspecialidadViewHolder(view)
    }

    override fun onBindViewHolder(holder: EspecialidadViewHolder, position: Int) {
        val item = lista[position]
        holder.nombre.text = item.nombre
        holder.doctor.text = item.doctor
        holder.horario.text = item.horario
        holder.imagen.setImageResource(item.imagenResId)
    }

    override fun getItemCount() = lista.size
}