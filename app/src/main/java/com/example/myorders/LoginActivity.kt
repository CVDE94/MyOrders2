package com.example.myorders

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import org.w3c.dom.Text

class LoginActivity : AppCompatActivity() {
//Variables
    private lateinit var txtUser:EditText
    private lateinit var txtPassword:EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Asignando a las variables los valores que se encuentran en los Txt

        txtUser=findViewById(R.id.txtUser)
        txtPassword=findViewById(R.id.txtPassword)
        progressBar=findViewById(R.id.progressBar2)

        //Creacion de la instancia para la Authenticacion
        auth= FirebaseAuth.getInstance()

    }
    fun forgotPassword (View:View){
        startActivity(Intent(this,ForgotPassActivity::class.java))
    }
    fun register (View:View){
        startActivity(Intent(this,RegisterActivity::class.java))
    }
    fun login (View:View){
        loginUser()
    }

    private fun loginUser(){
        val user:String=txtUser.text.toString()
        val password:String=txtPassword.text.toString()

        if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password))
        {
            progressBar2.visibility=View.VISIBLE

            auth.signInWithEmailAndPassword(user,password)
                .addOnCompleteListener(this){
                    task ->

                    if (task.isSuccessful){
                        action()
                    }else  {
                        Toast.makeText(this,"Error en la Autenticacion",Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
    private fun action(){
        startActivity(Intent(this,MainActivity::class.java))
    }
}


