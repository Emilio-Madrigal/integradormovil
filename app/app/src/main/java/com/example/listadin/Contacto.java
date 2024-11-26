package com.example.listadin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import global.info;

public class Contacto extends AppCompatActivity {
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contacto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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