package com.example.integrador;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.integrador.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import global.info;
import pojo.cita;
import pojo.paciente;

public class modify_user extends AppCompatActivity {
    EditText nombrem, edadm, pesom, alturam, telefonom, citahoram, citafecham;
    Spinner actm;
    Button anterior, modificar, siguiente;
    Integer posicion = -1;
    Toolbar toolbar;
    String Seleccionactividad;
    SharedPreferences archivo;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    cita dat=new cita ();

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


        citafecham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int dia, mes, ayo;
                Calendar actual=Calendar.getInstance();
                dia=actual.get(Calendar.DAY_OF_MONTH);
                mes=actual.get(Calendar.MONTH);
                ayo=actual.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(modify_user.this, android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateSetListener,ayo,mes,dia);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable (Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayofMonth) {
                dat.dia=dayofMonth;
                dat.mes=month+1;
                dat.Year=year;
                String cadena;

                cadena = " "+dat.dia+"/"+dat.mes+"/"+dat.Year;
                citafecham.setText(cadena);
            }
        };

        citahoram.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {hora();

            }
        });

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

        HashMap<String, String> datosPaciente = new HashMap<>();
        datosPaciente.put("nombre", nombrem.getText().toString());
        datosPaciente.put("edad", edadm.getText().toString());
        datosPaciente.put("peso", pesom.getText().toString());
        datosPaciente.put("altura", alturam.getText().toString());
        datosPaciente.put("telefono", telefonom.getText().toString());
        datosPaciente.put("actividad", Seleccionactividad);
        datosPaciente.put("hora_cita", citahoram.getText().toString());
        datosPaciente.put("fecha_cita", citafecham.getText().toString());

        JSONObject jsonObject = new JSONObject(datosPaciente);

        String idDentistaString = String.valueOf(posicion);

        String url = "http://10.0.2.2/bd/actualizar?id=" + idDentistaString;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, response -> {
            try {
                String status = response.getString("status");
                String message = response.getString("message");

                if (status.equals("success")) {
                    Toast.makeText(this, "Datos actualizados correctamente.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Error: " + message, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error al procesar la respuesta del servidor.", Toast.LENGTH_SHORT).show();
                Log.e("Actualizar", "Error al procesar la respuesta del servidor", e);
            }
        },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(this, "Error en la solicitud: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("Actualizar", "Error en la solicitud", error);
                }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
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
    private void hora() {
        int hr, min;
        Calendar actual = Calendar.getInstance();
        hr = actual.get(Calendar.HOUR_OF_DAY);
        min = actual.get(Calendar.MINUTE);

        TimePickerDialog tpd = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourofday, int minute) {
                dat.minutos = minute;
                dat.horas = hourofday;
                String cadena;
                cadena = " " + dat.horas + ":" + dat.minutos;
                citahoram.setText(cadena);
            }
        },hr,min,true);
        tpd.show();

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
