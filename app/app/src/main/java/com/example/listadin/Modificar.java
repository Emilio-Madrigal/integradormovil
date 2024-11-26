package com.example.listadin;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.listadin.R;

import global.info;
import pojo.equipo;

public class Modificar extends AppCompatActivity {
    EditText ne, p, jj, jg, je, noe;
    Button anterior, modificar, siguiente;
    Integer posicion = -1;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modificar);

        ne =findViewById(R.id.NEM);
        p =findViewById(R.id.PEM);
        jj =findViewById(R.id.JJEM);
        jg =findViewById(R.id.JGEM);
        je =findViewById(R.id.JEEM);
        noe =findViewById(R.id.NCEM);

        anterior = findViewById(R.id.Anterior);
        modificar = findViewById(R.id.Modificar);
        siguiente = findViewById(R.id.Siguiente);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        siguiente();

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
        Integer tamaño = info.lista.size();

        posicion = (posicion + 1) % tamaño; // si sobrepasa vuelve a cero
        mostrarEquipo();
    }

    private void actualizar() {
        equipo equipoActual = info.lista.get(posicion);
        equipoActual.setTeam(ne.getText().toString());
        equipoActual.setPoints(p.getText().toString());
        equipoActual.setWins(jj.getText().toString());
        equipoActual.setGames(jg.getText().toString());
        equipoActual.setTies(je.getText().toString());
        equipoActual.setContact(noe.getText().toString());
        Toast.makeText(this, "Datos actualizados", Toast.LENGTH_SHORT).show();
    }

    private void anterior() {
        Integer tamaño = info.lista.size();
        posicion = (posicion - 1 + tamaño) % tamaño;
        mostrarEquipo();
    }
    private void mostrarEquipo() {
        equipo equipoActual = info.lista.get(posicion);

        ne.setText(equipoActual.getTeam());
        p.setText(equipoActual.getPoints());
        jj.setText(equipoActual.getWins());
        jg.setText(equipoActual.getGames());
        je.setText(equipoActual.getTies());
        noe.setText(equipoActual.getContact());



    }
    @Override
    public void onOptionsMenuClosed(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        super.onOptionsMenuClosed(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.ver){

            Integer tamaño = info.lista.size();
            if (tamaño == 0) {
                Toast.makeText(this, "Lista vacía", Toast.LENGTH_SHORT).show();
            }
            else{
                Intent cambio8 = new Intent(this, ver.class);
                startActivity(cambio8);
            }
        }
        if(item.getItemId()==R.id.registro){
            Intent cambio8 = new Intent(this, MainActivity.class);
            startActivity(cambio8);
        }
        if(item.getItemId()==R.id.creador){
            Intent cambio8 = new Intent(this, Autor.class);
            startActivity(cambio8);
        }
        if(item.getItemId()==R.id.contacto){
            Intent cambio8 = new Intent(this, Contacto.class);
            startActivity(cambio8);
        }
        if(item.getItemId()==R.id.Modificar){

            Integer tamaño = info.lista.size();
            if (tamaño == 0) {
                Toast.makeText(this, "Lista vacía", Toast.LENGTH_SHORT).show();
            }
            else{

                Intent cambio8 = new Intent(this,Modificar.class);
                startActivity(cambio8);
            }
        }
        if(item.getItemId()==R.id.eliminar){
            Intent cambio8 = new Intent(this, Ver2.class);
            startActivity(cambio8);
        }
        return super.onOptionsItemSelected(item);
    }

}