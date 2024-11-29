package com.example.integrador;

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

public class modify_comida extends AppCompatActivity {
    EditText nombre,calorias;

    Button modificar,anterior,siguiente;
    Integer posicion = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modify_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        modificar = findViewById(R.id.Modificar);
        siguiente = findViewById(R.id.Siguiente);
        anterior = findViewById(R.id.Anterior);

        calorias = findViewById(R.id.calorias);
        nombre = findViewById(R.id.nombre);


        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anterior();
            }
        });

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar();
            }
        });
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siguiente();
            }
        });
    }

    private void siguiente() {
        Integer tamaño = info.listaComida.size();

        posicion = (posicion + 1) % tamaño; // si sobrepasa vuelve a cero
        mostrarEquipo();
    }

    private void actualizar() {
        comida comidaActual = info.listaComida.get(posicion);
        comidaActual.setCalorias (calorias.getText().toString());
        comidaActual.setNombre (nombre.getText().toString());

        Toast.makeText(this, "Datos actualizados", Toast.LENGTH_SHORT).show();
    }

    private void anterior() {
        Integer tamaño = info.listaComida.size();
        posicion = (posicion - 1 + tamaño) % tamaño;
        mostrarEquipo();
    }
    private void mostrarEquipo() {
        comida comidaActual = info.listaComida.get(posicion);
        calorias.setText (comidaActual.getCalorias ());
        nombre.setText (comidaActual.getNombre ());
    }
}
