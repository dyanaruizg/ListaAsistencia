package com.examen.android.listaasistencia

import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_groups.*

class ActivityGroups : AppCompatActivity() {

    lateinit var adapter: AdapterStudents
    lateinit var recyclerView: RecyclerView

    var grupo: Int = 1

    val alumno = ArrayList<Student>()
    val matriculas1 = ArrayList<String>()
    val asistencia1 = ArrayList<Int>()

    val alumno2 = ArrayList<Student>()
    val matriculas2 = ArrayList<String>()
    val asistencia2 = ArrayList<Int>()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groups)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        fab.setOnClickListener{
            if (grupo == 1) {
                enviarCorreo(matriculas1, asistencia1)
            } else {
                enviarCorreo(matriculas2, asistencia2)
            }
        }

        agregarAlumnos()
        agregarMatriculas()
        agregarAsistencias()

        adapter = AdapterStudents(alumno = alumno, asist = asistencia1)
        recyclerView = findViewById(R.id.listaGrupos)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_grupo1 -> {
                grupo = 1
                adapter = AdapterStudents(alumno = alumno, asist = asistencia1)
                recyclerView = findViewById(R.id.listaGrupos)
                recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
                recyclerView.adapter = adapter
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_grupo2 -> {
                grupo = 2
                adapter = AdapterStudents(alumno = alumno2, asist = asistencia2)
                recyclerView = findViewById(R.id.listaGrupos)
                recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
                recyclerView.adapter = adapter
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.correo -> {
                obtenerDialogoCorreo()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun obtenerDialogoCorreo() {
        val builder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_email, null)
        builder.setTitle(R.string.ingresar)
        builder.setView(dialogLayout)
                .setPositiveButton(R.string.guardar) { p0, p1 ->
                    val editText = dialogLayout.findViewById<EditText>(R.id.textoCorreo)
                    val correo = editText?.text.toString()
                    val preferences = getSharedPreferences("E-mail", Context.MODE_PRIVATE)
                    val editor = preferences.edit()
                    editor.putString("correo", correo)
                    editor.apply()
                }
                .setNegativeButton(R.string.cancelar) { p0, p1 -> p0?.cancel() }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun agregarAlumnos() {
        alumno.add(Student(R.drawable.minho, "Choi Min Ho", "15130223", true))
        alumno.add(Student(R.drawable.key, "Kim Ki Bum", "15130559", true))
        alumno.add(Student(R.drawable.taemin, "Lee Tae Min", "16130478", true))
        alumno.add(Student(R.drawable.onew, "Lee Jin Ki", "14130963", true))
        alumno.add(Student(R.drawable.jonghyun, "Kim Jong Hyun", "16130002", true))
        alumno.add(Student(R.drawable.jb, "Im Jae Bum", "15130170", true))
        alumno.add(Student(R.drawable.bambam, "Kunpimook Bhuwakul", "14130089", true))
        alumno.add(Student(R.drawable.mark, "Mark Yi En Tuan", "16130189", true))
        alumno.add(Student(R.drawable.jongsuk, "Lee Jong Suk", "16130053", true))
        alumno.add(Student(R.drawable.taehwan, "Lee Tae Hwan", "13130130", true))

        alumno2.add(Student(R.drawable.eunwoo, "Cha Eun Woo", "17130456", true))
        alumno2.add(Student(R.drawable.siwon, "Choi Si Won", "15130786", true))
        alumno2.add(Student(R.drawable.kangjoon, "Seo Kang Joon", "14130083", true))
        alumno2.add(Student(R.drawable.sungjae, "Yook Sung Jae", "15130028", true))
        alumno2.add(Student(R.drawable.haein, "Jung Hae In", "15130044", true))
        alumno2.add(Student(R.drawable.seojoon, "Park Seo Joon", "16130247", true))
        alumno2.add(Student(R.drawable.hyungsik, "Park Hyung Sik", "16130354", true))
        alumno2.add(Student(R.drawable.dongwook, "Lee Dong Wook", "14130258", true))
        alumno2.add(Student(R.drawable.moonbin, "Moon Bin", "15130846", true))
        alumno2.add(Student(R.drawable.mj, "Kim Myung Joon", "14130952", true))
    }

    fun agregarMatriculas() {
        matriculas1.add("15130223")
        matriculas1.add("15130559")
        matriculas1.add("16130478")
        matriculas1.add("14130963")
        matriculas1.add("16130002")
        matriculas1.add("15130170")
        matriculas1.add("14130089")
        matriculas1.add("16130189")
        matriculas1.add("16130053")
        matriculas1.add("13130130")

        matriculas2.add("17130456")
        matriculas2.add("15130786")
        matriculas2.add("14130083")
        matriculas2.add("15130028")
        matriculas2.add("15130044")
        matriculas2.add("16130247")
        matriculas2.add("16130354")
        matriculas2.add("14130258")
        matriculas2.add("15130846")
        matriculas2.add("14130952")
    }

    fun agregarAsistencias() {
        asistencia1.add(1)
        asistencia1.add(1)
        asistencia1.add(1)
        asistencia1.add(1)
        asistencia1.add(1)
        asistencia1.add(1)
        asistencia1.add(1)
        asistencia1.add(1)
        asistencia1.add(1)
        asistencia1.add(1)

        asistencia2.add(1)
        asistencia2.add(1)
        asistencia2.add(1)
        asistencia2.add(1)
        asistencia2.add(1)
        asistencia2.add(1)
        asistencia2.add(1)
        asistencia2.add(1)
        asistencia2.add(1)
        asistencia2.add(1)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun enviarCorreo(matriculas: ArrayList<String>, asist: ArrayList<Int>) {
        val preferences = getSharedPreferences("E-mail", Context.MODE_PRIVATE)
        val correo = preferences.getString("correo", "")
        val to = arrayOf(correo)
        val emailIntent = Intent(Intent.ACTION_SEND)
        val cal = Calendar.getInstance()
        val dia = cal.get(Calendar.DAY_OF_MONTH)
        val mes = cal.get(Calendar.MONTH)
        val ano = cal.get(Calendar.YEAR)
        var mensaje: String? = ""
        val fecha = "$dia/$mes/$ano"
        val titulo = "Matrícula    Asistencia"

        for (i in 0..9) {
            mensaje += matriculas[i] + "          " + asist[i] + "\n"
        }

        emailIntent.data = Uri.parse("mailto:")
        emailIntent.type = "text/plain"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to)
        emailIntent.putExtra(Intent.EXTRA_CC, "")
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Examen#1 - Diana Ruiz García")
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Lista de Asistencia - $fecha\n\n$titulo\n$mensaje")

        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email..."))
            finish()
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(this@ActivityGroups,
                    "No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show()
        }

    }

}
