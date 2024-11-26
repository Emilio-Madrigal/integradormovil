package com.example.listadin;

import static global.info.lista;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import adaptador.adaptadorelim;
import global.info;
import pojo.equipo;

public class Ver2 extends AppCompatActivity {

    RecyclerView rv2;
    Context context;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver2);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rv2=findViewById(R.id.recyclerviewE);
        adaptadorelim av = new adaptadorelim();
        av.context=this;
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv2.setLayoutManager(llm);
        rv2.setAdapter(av);

    }
    public void eliminar(View view) {

        List<equipo> Trashcan = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            equipo item = lista.get(i);
            if (item.isChecked()) {
                Trashcan.add(item);
            }
        }
        lista.removeAll(Trashcan);
        rv2.getAdapter().notifyDataSetChanged();
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



