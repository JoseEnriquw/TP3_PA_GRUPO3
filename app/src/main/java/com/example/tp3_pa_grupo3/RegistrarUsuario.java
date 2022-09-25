package com.example.tp3_pa_grupo3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import OpenHelper.SQLite_OpenHelper;
import Util.UtilsSQL;

public class RegistrarUsuario extends AppCompatActivity {

    SQLite_OpenHelper dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
    }
    public void Agregar(View view)
    {
        String repetirContrasenia =((EditText)findViewById(R.id.et_RepetirContrasenia)).getText().toString();
        String Contrasenia =((EditText)findViewById(R.id.et_Contrasenia)).getText().toString();
        if(repetirContrasenia.equals(Contrasenia))
        {
            Usuarios usuario=new Usuarios();
            usuario.setNombre(((EditText)findViewById(R.id.et_Nombre)).getText().toString());
            usuario.setCorreo(((EditText)findViewById(R.id.et_Correo)).getText().toString());
            usuario.setContrasenia(Contrasenia);

            dao=new SQLite_OpenHelper(this);
            if (!isUsuarioExiste(usuario)) {
                dao.insert(usuario);

                Toast.makeText(this, "Usuario agregado con exito", Toast.LENGTH_LONG).show();

                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
        } else{
            Toast.makeText(this,"Verifique que las contraseñas sean iguales",Toast.LENGTH_LONG).show();
            ((EditText)findViewById(R.id.et_Contrasenia)).setText("");
            ((EditText)findViewById(R.id.et_RepetirContrasenia)).setText("");
        }
    }

   private boolean isUsuarioExiste(Usuarios usuario)
   {
       dao=new SQLite_OpenHelper(this);
       if(dao.existeRegistro(UtilsSQL.SELECT_USUARIO_BY_NAME,new String[]{usuario.getNombre()}))
       {
           Toast.makeText(this,"El nombre de usuario ingresado ya existe :(",Toast.LENGTH_LONG).show();
       }
       else if(dao.existeRegistro(UtilsSQL.SELECT_USUARIO_BY_MAIL,new String[]{usuario.getCorreo()}))
       {
           Toast.makeText(this,"El  correo ingresado ya está registrado :(",Toast.LENGTH_LONG).show();
       }
       else
       {
           return  false;
       }

       return true;
   }
}