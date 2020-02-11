package com.julia.organizze

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.heinrichreimersoftware.materialintro.app.IntroActivity
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide
import com.julia.organizze.activity.CadastroActivity
import com.julia.organizze.activity.LoginActivity
import com.julia.organizze.activity.PrincipalActivity
import com.julia.organizze.config.ConfiguracaoFirebase

class MainActivity : IntroActivity() {

    val autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)


        isButtonBackVisible = false
        isButtonNextVisible = false


        addSlide(FragmentSlide.Builder()
            .background(android.R.color.white)
            .fragment(R.layout.intro_1)
            .build())

        addSlide(FragmentSlide.Builder()
            .background(android.R.color.white)
            .fragment(R.layout.intro_2)
            .build())

        addSlide(FragmentSlide.Builder()
            .background(android.R.color.white)
            .fragment(R.layout.intro_3)
            .build())

        addSlide(FragmentSlide.Builder()
            .background(android.R.color.white)
            .fragment(R.layout.intro_4)
            .build())

        addSlide(FragmentSlide.Builder()
            .background(android.R.color.white)
            .fragment(R.layout.intro_cadastro)
            .canGoForward(false)
            .build())
    }

    override fun onStart() {
        super.onStart()
        verificaUsuarioLogado()
    }

    fun btCadastrar(view: View){
        val intent = Intent(this, CadastroActivity::class.java)
        startActivity(intent)
    }

    fun btEntrar(view: View){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun verificaUsuarioLogado(){
        //autenticacao.signOut()
        if(autenticacao.currentUser != null){
            abrirTelaPrincipal()
        }
    }

    fun abrirTelaPrincipal(){
        val intent = Intent(this, PrincipalActivity::class.java)
        startActivity(intent)
    }

}
