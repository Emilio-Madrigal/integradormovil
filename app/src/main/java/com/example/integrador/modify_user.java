package com.example.integrador;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

import global.info;
import pojo.cita;
import pojo.paciente;

public class modify_user extends AppCompatActivity {
    EditText nombre,edad, peso, altura, telefono, hora,fecha;
    Spinner actividad, meta;
    Button modificar,anterior,siguiente;
    Integer posicion = -1;
    String Seleccionactividad;
    SharedPreferences archivo;

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    cita dat=new cita ();

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
        nombre=findViewById (R.id.nombre);
        modificar = findViewById(R.id.Modificar);
        siguiente=findViewById (R.id.siguiente);
        anterior=findViewById (R.id.anterior);
        edad = findViewById(R.id.edad);
        peso = findViewById(R.id.peso);
        altura = findViewById(R.id.altura);
        telefono = findViewById(R.id.telefono);

        actividad = findViewById(R.id.spinner_act);

        hora=findViewById (R.id.citahora);
        fecha=findViewById (R.id.citafecha);

        fecha.setOnClickListener(new View.OnClickListener() {
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
                fecha.setText(cadena);
            }
        };

        hora.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {hora();

            }
        });


        // Configuración de los spinners
        actividad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Seleccionactividad = actividad.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
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

        posicion = (posicion + 1) % tamaño; // si sobrepasa vuelve a cero
        mostrarpaciente ();
    }

    private void actualizar() {
        paciente pacienteActual = info.listapaciente.get(posicion);

        // Actualizar los campos del paciente logueado
        pacienteActual.setNombre (nombre.getText ().toString ());
        pacienteActual.setEdad(edad.getText().toString());
        pacienteActual.setPeso(peso.getText().toString());
        pacienteActual.setAltura(altura.getText().toString());
        pacienteActual.setActividad(Seleccionactividad);
        pacienteActual.setTelefono(telefono.getText().toString());
        pacienteActual.setHorac (hora.getText ().toString ());
        pacienteActual.setFechac (fecha.getText ().toString ());


        Toast.makeText(this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show();
    }
    private void anterior() {
        Integer tamaño = info.listapaciente.size();
        posicion = (posicion - 1 + tamaño) % tamaño;
        mostrarpaciente ();
    }

    private void mostrarpaciente() {
        paciente pacienteActual = info.listapaciente.get(posicion);

        // Mostrar los valores actuales en los campos correspondientes
        edad.setText(pacienteActual.getEdad());
        peso.setText(pacienteActual.getPeso());
        altura.setText(pacienteActual.getAltura());
        telefono.setText(pacienteActual.getTelefono());


        // Configurar los valores iniciales de los spinners
        if (pacienteActual.getActividad() != null) {
            int actividadPosition = ((android.widget.ArrayAdapter<String>) actividad.getAdapter())
                    .getPosition(pacienteActual.getActividad());
            actividad.setSelection(actividadPosition);
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
                hora.setText(cadena);
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
