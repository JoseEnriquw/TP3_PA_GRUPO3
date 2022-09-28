package com.example.tp3_pa_grupo3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import OpenHelper.SQLite_OpenHelper;

public class Login extends AppCompatActivity {
    TextView tv_Registrarse;
    SQLite_OpenHelper bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bd = new SQLite_OpenHelper(this);
        tv_Registrarse = (TextView)findViewById(R.id.tv_Registrarse);
        tv_Registrarse.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(),RegistrarUsuario.class);
            startActivity(i);
        });
    }
    public void Btn_Login(View view){

        String correo = String.valueOf(((EditText)findViewById(R.id.et_Usuario)).getText());
        String contrasenia = String.valueOf(((EditText)findViewById(R.id.et_Password)).getText());

        if(correo.isEmpty() || contrasenia.isEmpty()){
            Toast.makeText(this,"Debe completar todos los campos",Toast.LENGTH_LONG).show();
        }else{
            Usuarios us = new Usuarios(correo,contrasenia);
            if ((us=bd.ValidarLogin(us))!=null){
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("usuario",us);
                startActivity(i);
            }
            else{
                Toast.makeText(this,"Usuario o contraseña incorrectos",Toast.LENGTH_LONG).show();
            }
        }



    }



}