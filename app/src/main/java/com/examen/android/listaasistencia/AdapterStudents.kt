package com.examen.android.listaasistencia

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView

class AdapterStudents (var alumno: ArrayList<Student>, var asist: ArrayList<Int>)
    : RecyclerView.Adapter<AdapterStudents.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.item_student, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return alumno.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val posAlumno: Student = alumno[p1]
        p0.imagen?.setImageResource(posAlumno.imagen)
        p0.nombre?.text = posAlumno.nombre
        p0.matricula?.text = posAlumno.matricula
        p0.asistencia?.isChecked = posAlumno.asistencia
        p0.asistencia?.setOnClickListener{
            posAlumno.asistencia = p0.asistencia.isChecked
            if (posAlumno.asistencia) {
                asist[p1] = 1
            } else {
                asist[p1] = 0
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen = itemView.findViewById<ImageView>(R.id.imagen)
        val nombre = itemView.findViewById<TextView>(R.id.nombre)
        val matricula = itemView.findViewById<TextView>(R.id.matricula)
        val asistencia = itemView.findViewById<CheckBox>(R.id.paseLista)
    }

}