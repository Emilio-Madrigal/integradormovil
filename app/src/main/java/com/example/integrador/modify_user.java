package com.example.integrador;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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
    Integer posicion = 1;
    Toolbar toolbar;
    String Seleccionactividad;
    TextView tamano;
    Integer tamaño;
    SharedPreferences archivo;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    cita dat=new cita ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modify_user);
        archivo = this.getSharedPreferences("sesion", Context.MODE_PRIVATE);
        tamano=findViewById (R.id.tamaño);
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
        mostrarEquipo ();
    }

    private void actualizar() {

        Map<String, String> params = new HashMap<>();
        params.put("nombre", nombrem.getText ().toString());
        params.put("edad", edadm.getText().toString());
        params.put("peso", pesom.getText().toString());
        params.put("altura", alturam.getText().toString());
        params.put("telefono", telefonom.getText().toString());
        params.put("actividad", Seleccionactividad); // Incluido
        params.put("hora_cita", citahoram.getText().toString());
        params.put("fecha_cita", citafecham.getText().toString());
        JSONObject jsonObject = new JSONObject(params);

        String id = String.valueOf(posicion+1); // Obtén el ID de la posición actual
        Log.d("Actualizar", "ID enviado al servidor: " + id);
        String url = "http://10.0.2.2/bd/actualizar.php?id="+id;

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
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
    private void siguiente() {
        if (tamaño == 0) {
            Toast.makeText(this, "Lista vacía", Toast.LENGTH_SHORT).show();
            return;
        }
        posicion = (posicion + 1) % tamaño; // Avanza circularmente a la siguiente posición
        mostrarEquipo();
    }

    private void anterior() {
        if (tamaño == 0) {
            Toast.makeText(this, "Lista vacía", Toast.LENGTH_SHORT).show();
            return;
        }
        posicion = (posicion - 1 + tamaño) % tamaño; // Retrocede circularmente a la anterior posición
        mostrarEquipo();
    }

    private void mostrarEquipo() {
        String id = String.valueOf(posicion + 1); // Asegúrate de que el ID corresponde al valor en la posición del índice
        Log.d("Actualizar", "ID enviado al servidor: " + id);
        String url = "http://10.0.2.2/bd/obtener_unpaciente.php?id=" + id;
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("status").equals("success")) {
                                JSONObject paciente = response.getJSONObject("data");
                                tamaño = response.getInt("total");

                                // Establecer los datos en los TextView
                                nombrem.setText(paciente.optString("nombre", ""));
                                edadm.setText(paciente.optString("edad", ""));
                                pesom.setText(paciente.optString("peso", ""));
                                alturam.setText(paciente.optString("altura", ""));
                                telefonom.setText(paciente.optString("telefono", ""));
                                citahoram.setText(paciente.optString("calificacion", ""));
                                citafecham.setText(paciente.optString("hora_apertura", ""));

                                // Obtener la especialidad del paciente desde el servidor
                                String especialidadActual = paciente.optString("actividad", "");

                                // Buscar la posición de la especialidad en el adaptador del Spinner
                                ArrayAdapter adapter = (ArrayAdapter) actm.getAdapter();
                                int position = adapter.getPosition(especialidadActual);

                                // Seleccionar la especialidad en el Spinner
                                if (position >= 0) { // Asegurarse de que la posición sea válida
                                    actm.setSelection(position);
                                }

                                tamano.setText("" + tamaño);
                            } else {
                                Toast.makeText(modify_user.this, "No se encontró información del dentista", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(modify_user.this, "Error al procesar los datos", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(modify_user.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(request);
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

                Intent cambio8 = new Intent (this, ver.class);
                startActivity (cambio8);
        }

        if (item.getItemId () == R.id.create) {
            Intent cambio8 = new Intent (this, user_info.class);
            startActivity (cambio8);
        }

        if (item.getItemId () == R.id.update) {
                Intent cambio8 = new Intent (this, modify_user.class);
                startActivity (cambio8);
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
