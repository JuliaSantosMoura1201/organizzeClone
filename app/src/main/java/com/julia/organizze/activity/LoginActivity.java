package com.julia.organizze.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.julia.organizze.R;
import com.julia.organizze.config.ConfiguracaoFirebase;
import com.julia.organizze.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private EditText campoEmail, campoSenha;
    private Button buttonEntrar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campoEmail = findViewById(R.id.editEmailLogin);
        campoSenha = findViewById(R.id.editSenhaLogin);
        buttonEntrar = findViewById(R.id.buttonEntrar);

        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();

                if(!textoEmail.isEmpty()){
                    if(!textoSenha.isEmpty()){

                        usuario = new Usuario();
                        usuario.setEmail(textoEmail);
                        usuario.setSenha(textoSenha);

                        validarLogin();

                    }else{
                        Toast.makeText(getApplicationContext(), "Preencha a senha!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Preencha o email!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void validarLogin(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            abrirTelaPrinciapal();
                        }else{

                            String excecao = "";

                            try {
                                throw task.getException();

                            }catch (FirebaseAuthInvalidUserException e){
                                excecao = "Usuário não está cadastrado!";
                            }catch (FirebaseAuthInvalidCredentialsException e){
                                excecao = "Email e senha não correspondem a um usuário cadastrado";
                            }catch (Exception e){
                                excecao = "Erro ao fazer login" + e.getMessage();
                                e.printStackTrace();
                            }
                            Toast.makeText(getApplicationContext(), excecao, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void abrirTelaPrinciapal(){
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
        finish();
    }

}
