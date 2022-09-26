package com.example.tp3_pa_grupo3;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

public class DialogoAgregarParquimetro {


    public interface Getdatosagregar{
        void Resultado (String matricula, String tiempo);
    }

    private Getdatosagregar interfaz;

    public DialogoAgregarParquimetro(Context contexto, Getdatosagregar actividad) {

        interfaz = actividad;
        final Dialog dialogo = new Dialog(contexto);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(false);
        dialogo.setTitle("Registro parquimetro");
        dialogo.setContentView(R.layout.registro_parquimetros);


        TextView agregar = ((TextView) dialogo.findViewById(R.id.btn_agregar));
        TextView cancelar = ((TextView) dialogo.findViewById(R.id.btn_cancelar));

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String matricula = ((EditText) dialogo.findViewById(R.id.txt_matricula)).getText().toString();
                String tiempo = ((EditText) dialogo.findViewById(R.id.txt_tiempo)).getText().toString();
                interfaz.Resultado(matricula, tiempo);
                dialogo.dismiss();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo.dismiss();
            }
        });

        dialogo.show();
    }
}
