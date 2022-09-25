package com.example.tp3_pa_grupo3;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import OpenHelper.SQLite_OpenHelper;

public class MainActivity extends AppCompatActivity {
    SQLite_OpenHelper dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}