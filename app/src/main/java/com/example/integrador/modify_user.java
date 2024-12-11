package com.example.integrador;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.integrador.R;

import global.info;
import pojo.paciente;

public class modify_user extends AppCompatActivity {
    EditText nombrem, edadm, pesom, alturam, telefonom, citahoram, citafecham;
    Spinner actm;
    Button anterior, modificar, siguiente;
    Integer posicion = -1;
    Toolbar toolbar;
    String Seleccionactividad;
    SharedPreferences archivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modify_user);

        nombrem = findViewById(R.id.nombrem);
        edadm = findViewById(R.id.edadm);
        pesom = findViewById(R.id.pesom);
        alturam = findViewById(R.id.alturam);
        telefonom = findViewById(R.id.telefonom);
        citahoram = findViewById(R.id.citahoram);
        citafecham = findViewById(R.id.citafecham);
        actm = findViewById(R.id.actm);

        anterior = findViewById(R.id.anterior);
        modificar = findViewById(R.id.Modificar);
        siguiente = findViewById(R.id.siguiente);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Seleccionactividad = actm.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
        Integer tamaño = info.listapaciente.size();

        posicion = (posicion + 1) % tamaño; // Si sobrepasa vuelve a cero
        mostrarPaciente();
    }

    private void actualizar() {
        paciente pacienteActual = info.listapaciente.get(posicion);
        pacienteActual.setNombre(nombrem.getText().toString());
        pacienteActual.setEdad(edadm.getText().toString());
        pacienteActual.setPeso(pesom.getText().toString());
        pacienteActual.setAltura(alturam.getText().toString());
        pacienteActual.setTelefono(telefonom.getText().toString());
        pacienteActual.setActividad(Seleccionactividad);
        pacienteActual.setHorac(citahoram.getText().toString());
        pacienteActual.setFechac(citafecham.getText().toString());

        Toast.makeText(this, "Datos actualizados", Toast.LENGTH_SHORT).show();
    }

    private void anterior() {
        Integer tamaño = info.listapaciente.size();
        posicion = (posicion - 1 + tamaño) % tamaño;
        mostrarPaciente();
    }

    private void mostrarPaciente() {
        paciente pacienteActual = info.listapaciente.get(posicion);

        nombrem.setText(pacienteActual.getNombre());
        edadm.setText(pacienteActual.getEdad());
        pesom.setText(pacienteActual.getPeso());
        alturam.setText(pacienteActual.getAltura());
        telefonom.setText(pacienteActual.getTelefono());
        citahoram.setText(pacienteActual.getHorac());
        citafecham.setText(pacienteActual.getFechac());

        // Para el Spinner, selecciona el índice correspondiente
        String actividad = pacienteActual.getActividad();
        for (int i = 0; i < actm.getCount(); i++) {
            if (actm.getItemAtPosition(i).toString().equals(actividad)) {
                actm.setSelection(i);
                break;
            }
        }
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
