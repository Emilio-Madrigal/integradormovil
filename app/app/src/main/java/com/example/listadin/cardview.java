package com.example.listadin;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.widget.Toolbar;

import global.info;

public class cardview extends AppCompatActivity {

    Button llamar;
    TextView NombreEC,PuntosC,JuegosJC,JuegosGC,JuegosEC,NocontactoEC;
    Toolbar toolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cardview);
        NombreEC=findViewById(R.id.NE);
        PuntosC=findViewById(R.id.PE);
        JuegosJC=findViewById(R.id.JJE);
        JuegosGC=findViewById(R.id.JGE);
        JuegosEC=findViewById(R.id.JEE);
        NocontactoEC=findViewById(R.id.NCE);
        toolbar=findViewById(R.id.toolbarCard);
        setSupportActionBar(toolbar);

        llamar = findViewById(R.id.llamar);

        llamar.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                llamar();
            }
        });

        int posicion;
        posicion=getIntent().getIntExtra("posicion", -1);
        NombreEC.setText(info.lista.get(posicion).getTeam());
        PuntosC.setText(info.lista.get(posicion).getPoints());
        JuegosJC.setText(info.lista.get(posicion).getWins());
        JuegosGC.setText(info.lista.get(posicion).getGames());
        JuegosEC.setText(info.lista.get(posicion).getTies());
        NocontactoEC.setText(info.lista.get(posicion).getContact());
    }

    private void llamar() {
        Intent llamada = new Intent(Intent.ACTION_CALL);
        llamada.setData(Uri.parse("tel: "+NocontactoEC.getText().toString()));
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.CALL_PHONE
        },10);
        startActivity(llamada);

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