package com.example.integrador;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import global.info;
import pojo.comida;
import pojo.paciente;

public class ingreso_comida extends AppCompatActivity {
    Button guardar;
    EditText nombre,calorias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ingreso_comida);

        guardar=findViewById(R.id.guardarB2);

        nombre=findViewById(R.id.nombreComida);
        calorias=findViewById(R.id.calorias);




        guardar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                guardardor();
            }
        });
    }
    public void guardardor(){
        comida unacomida = new comida();

        unacomida.setNombre(nombre.getText().toString());
        unacomida.setCalorias (calorias.getText ().toString ());
        info.listaComida.add(unacomida);


        Toast.makeText(this, "Elementos guardados", Toast.LENGTH_SHORT).show();


        nombre.setText("");
        calorias.setText("");

    }
}