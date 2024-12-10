package com.example.integrador;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.widget.Toolbar;

import global.info;

public class cardview extends AppCompatActivity {

    Spinner genero,actividad;
    Button guardar,llamarB;
    TextView nombre,apellido,edad,peso,altura,telefono,hora,fecha;
    String Selecciongenero,Seleccionactividad;
    Toolbar toolbar;
    SharedPreferences archivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cardview);
      nombre=findViewById (R.id.nombrec);
      apellido=findViewById (R.id.apellidoc);
      edad=findViewById (R.id.edadc);
      peso=findViewById (R.id.pesoc);
      altura=findViewById (R.id.alturac);
      telefono=findViewById (R.id.telefonoc);
      toolbar=findViewById(R.id.toolbarCard);
        setSupportActionBar(toolbar);

        llamarB = findViewById(R.id.llamarc);

        llamarB.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                llamar();
            }
        });

        int posicion;
        posicion=getIntent().getIntExtra("posicion", -1);
        nombre.setText(info.listapaciente.get(posicion).getNombre ());
        apellido.setText(info.listapaciente.get(posicion).getApellido ());
        edad.setText(info.listapaciente.get(posicion).getEdad ());
        peso.setText(info.listapaciente.get(posicion).getPeso ());
        altura.setText(info.listapaciente.get(posicion).getAltura ());
        telefono.setText(info.listapaciente.get(posicion).getTelefono ());

    }

    private void llamar() {
        Intent llamada = new Intent(Intent.ACTION_CALL);
        llamada.setData(Uri.parse("tel: "+telefono.getText().toString()));
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.CALL_PHONE
        },10);
        startActivity(llamada);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        getMenuInflater ().inflate (R.menu.menu, menu);
        super.onOptionsMenuClosed (menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate (R.menu.menu, menu);
        return super.onCreateOptionsMenu (menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId () == R.id.read) {

            Integer tamaño = info.listapaciente.size ();
            if (tamaño == 0) {
                Toast.makeText (this, "Lista vacía", Toast.LENGTH_SHORT).show ();
            } else {
                Intent cambio8 = new Intent (this, ver.class);
                startActivity (cambio8);
            }
        }

        if (item.getItemId () == R.id.create) {
            Intent cambio8 = new Intent (this, user_info.class);
            startActivity (cambio8);
        }

        if (item.getItemId () == R.id.update) {

            Integer tamaño = info.listapaciente.size ();
            if (tamaño == 0) {
                Toast.makeText (this, "Lista vacía", Toast.LENGTH_SHORT).show ();
            } else {

                Intent cambio8 = new Intent (this, modify_user.class);
                startActivity (cambio8);
            }
        }
        if (item.getItemId () == R.id.delete) {
            Intent cambio8 = new Intent (this, ver2.class);
            startActivity (cambio8);
        }
        if (item.getItemId () == R.id.logout) {
            if (archivo.contains ("id_usuario")) {
                SharedPreferences.Editor editor = archivo.edit ();
                editor.remove ("id_usuario");
                editor.commit ();
                Intent x = new Intent (this, MainActivity.class);
                startActivity (x);
                finish ();
            }

        }
        return super.onOptionsItemSelected(item);
    }
}