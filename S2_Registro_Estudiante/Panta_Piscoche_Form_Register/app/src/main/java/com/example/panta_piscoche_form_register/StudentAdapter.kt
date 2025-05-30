package com.example.panta_piscoche_form_register

import Student
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(private val estudiantes: List<Student>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onEditClick(student: Student)
        fun onDeleteClick(student: Student)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvNombre)
        val tvApellidos: TextView = view.findViewById(R.id.tvApellidos)
        val tvCodigo: TextView = view.findViewById(R.id.tvCodigo)
        val tvCorreo: TextView = view.findViewById(R.id.tvCorreo)
        val btnEditar: Button = view.findViewById(R.id.btnEditar)
        val btnEliminar: Button = view.findViewById(R.id.btnEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_estudiante, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = estudiantes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val est = estudiantes[position]
        holder.tvNombre.text = "Nombres: ${est.nombres}"
        holder.tvApellidos.text = "Apellidos: ${est.apellidos}"
        holder.tvCodigo.text = "Código: ${est.codigo}"
        holder.tvCorreo.text = "Correo: ${est.correo}"

        holder.btnEditar.setOnClickListener {
            listener.onEditClick(est)
        }

        holder.btnEliminar.setOnClickListener {
            listener.onDeleteClick(est)
        }
    }
}