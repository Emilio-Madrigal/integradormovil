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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import global.info;

public class user_info extends AppCompatActivity {
    Spinner genero, actividad;
    Button guardar;
    EditText nombre, apellido, edad, peso, altura, telefono, hora, fecha;
    String Selecciongenero, Seleccionactividad;
    Toolbar toolbar;
    SharedPreferences archivo;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        archivo = this.getSharedPreferences("sesion", Context.MODE_PRIVATE);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        guardar = findViewById(R.id.guardarB);

        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        peso = findViewById(R.id.peso);
        altura = findViewById(R.id.altura);
        telefono = findViewById(R.id.telefono);
        edad = findViewById(R.id.edad);

        genero = findViewById(R.id.spinner_gen);
        actividad = findViewById(R.id.spinner_act);

        hora = findViewById(R.id.citahora);
        fecha = findViewById(R.id.citafecha);

        fecha.setOnClickListener(view -> {
            int dia, mes, ayo;
            Calendar actual = Calendar.getInstance();
            dia = actual.get(Calendar.DAY_OF_MONTH);
            mes = actual.get(Calendar.MONTH);
            ayo = actual.get(Calendar.YEAR);

            DatePickerDialog dialog = new DatePickerDialog(user_info.this, android.R.style.Theme_Holo_Dialog_MinWidth,
                    mDateSetListener, ayo, mes, dia);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });

        mDateSetListener = (datePicker, year, month, dayofMonth) -> {
            String cadena = " " + dayofMonth + "/" + (month + 1) + "/" + year;
            fecha.setText(cadena);
        };

        hora.setOnClickListener(view -> mostrarHora());

        genero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Selecciongenero = genero.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        actividad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Seleccionactividad = actividad.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        guardar.setOnClickListener(view -> guardardor());
    }

    public void guardardor() {
        Log.d("Spinner", "Genero seleccionado: " + Selecciongenero);
        Log.d("Spinner", "Actividad seleccionada: " + Seleccionactividad);
        Log.d("fecha", "la hora es " + hora.getText().toString());
        Log.d("fecha", "la fecha es " + fecha.getText().toString());
        Log.d("info", "la info es " + nombre.getText().toString());
        Log.d("info", "la info es " + apellido.getText().toString());
        Log.d("info", "la info es " + edad.getText().toString());
        Log.d("info", "la info es " + peso.getText().toString());
        Log.d("info", "la info es " + altura.getText().toString());
        Log.d("info", "la info es " + telefono.getText().toString());

        String url = "http://10.0.2.2/bd/insertar_paciente.php";

        Map<String, String> params = new HashMap<>();
        params.put("nombre", nombre.getText().toString());
        params.put("apellido", apellido.getText().toString());
        params.put("genero", Selecciongenero); // Incluido
        params.put("edad", edad.getText().toString());
        params.put("peso", peso.getText().toString());
        params.put("altura", altura.getText().toString());
        params.put("telefono", telefono.getText().toString());
        params.put("actividad", Seleccionactividad); // Incluido
        params.put("hora_cita", hora.getText().toString());
        params.put("fecha_cita", fecha.getText().toString());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                response -> {
                    try {
                        boolean success = response.getBoolean("success");
                        String message = response.getString("message");
                        Toast.makeText(user_info.this, message, Toast.LENGTH_SHORT).show();

                        if (success) {
                            limpiarCampos();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(user_info.this, "Error en la respuesta", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    String errorMessage = "Error en la conexión: " + error.getMessage();
                    if (error.networkResponse != null && error.networkResponse.data != null) {
                        errorMessage += " - " + new String(error.networkResponse.data);
                    }
                    Toast.makeText(user_info.this, errorMessage, Toast.LENGTH_SHORT).show();
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private void limpiarCampos() {
        nombre.setText("");
        apellido.setText("");
        edad.setText("");
        peso.setText("");
        altura.setText("");
        telefono.setText("");
        fecha.setText("");
        hora.setText("");
        actividad.setSelection(0);
        genero.setSelection(0);
    }

    private void mostrarHora() {
        int hr, min;
        Calendar actual = Calendar.getInstance();
        hr = actual.get(Calendar.HOUR_OF_DAY);
        min = actual.get(Calendar.MINUTE);

        TimePickerDialog tpd = new TimePickerDialog(this, (timePicker, hourofday, minute) -> {
            String cadena = hourofday + ":" + String.format("%02d", minute);
            hora.setText(cadena);
        }, hr, min, true);
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
        if (item.getItemId () == R.id.creador) {
            Intent cambio8 = new Intent (this, autor.class);
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
