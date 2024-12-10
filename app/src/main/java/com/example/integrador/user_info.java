package com.example.integrador;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.SharedPreferences;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import global.info;
import pojo.cita;
import pojo.paciente;

public class user_info extends AppCompatActivity {
Spinner genero,actividad;
Button guardar;
EditText nombre,apellido,edad,peso,altura,telefono,hora,fecha;
String Selecciongenero,Seleccionactividad;
Toolbar toolbar;
SharedPreferences archivo;

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    cita dat=new cita ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        toolbar=findViewById (R.id.toolbar);
        setSupportActionBar(toolbar);
        guardar=findViewById(R.id.guardarB);

        nombre=findViewById(R.id.nombre);
        apellido=findViewById(R.id.apellido);
        peso=findViewById(R.id.peso);
        altura=findViewById(R.id.altura);
        telefono=findViewById(R.id.telefono);
        edad=findViewById(R.id.edad);

        genero=findViewById(R.id.spinner_gen);
        actividad=findViewById(R.id.spinner_act);

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

                DatePickerDialog dialog = new DatePickerDialog(user_info.this, android.R.style.Theme_Holo_Dialog_MinWidth,
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

        genero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Selecciongenero = genero.getItemAtPosition(i).toString();//position es la que tienen el string de lo que se selecciono
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
        guardar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                guardardor();
            }
        });
    }

    public void guardardor(){



        paciente unpaciente = new paciente();

        
        unpaciente.setNombre(nombre.getText().toString());
        unpaciente.setApellido(apellido.getText().toString());
        unpaciente.setGenero(Selecciongenero);
        unpaciente.setEdad(edad.getText().toString());
        unpaciente.setPeso(peso.getText().toString());
        unpaciente.setAltura(altura.getText().toString());
        unpaciente.setActividad(Seleccionactividad);
        unpaciente.setTelefono(telefono.getText().toString());
        unpaciente.setFechac (fecha.getText ().toString ());
        unpaciente.setHorac (hora.getText ().toString ());
        String nombree,ape,genero,Edad,Altura,Peso,Telefono,Actividad,HoraC,FechaC;





        nombree=nombre.getText().toString();
        ape=apellido.getText().toString();
        genero=Selecciongenero;
        Edad=edad.getText().toString();
        Peso=peso.getText().toString();
        Altura=altura.getText().toString();
        Actividad=Seleccionactividad;
        Telefono=telefono.getText().toString();
        HoraC=fecha.getText ().toString ();
        FechaC=hora.getText ().toString ();


        info.listapaciente.add(unpaciente);

        Map<String,String> params = new HashMap<> ();
        params.put("nombre",nombree);
        params.put ("ape",ape);
        params.put ("genero",genero);
        params.put ("edad",Edad);
        params.put ("peso",Peso);
        params.put ("altura",Altura);
        params.put ("telefono",Telefono);
        params.put ("actividad",Actividad);
        params.put ("hora",HoraC);
        params.put ("fecha",FechaC);
        
        Toast.makeText(this, "Elementos guardados", Toast.LENGTH_SHORT).show();

        
        nombre.setText("");
        apellido.setText("");
        edad.setText("");
        peso.setText("");
        altura.setText("");
        telefono.setText("");
        fecha.setText ("");
        hora.setText ("");


        actividad.setSelection(0);
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