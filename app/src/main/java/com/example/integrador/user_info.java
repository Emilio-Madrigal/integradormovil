package com.example.integrador;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import global.info;
import pojo.paciente;

public class user_info extends AppCompatActivity {
Spinner genero,actividad,meta;
Button guardar;
EditText nombre,apellido,edad,peso,altura,telefono,email;
String Selecciongenero,Seleccionactividad,Seleccionmeta;
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
        guardar=findViewById(R.id.guardarB);

        nombre=findViewById(R.id.nombre);
        apellido=findViewById(R.id.apellido);
        peso=findViewById(R.id.peso);
        altura=findViewById(R.id.altura);
        telefono=findViewById(R.id.telefono);
        email=findViewById(R.id.email);
        edad=findViewById(R.id.edad);

        genero=findViewById(R.id.spinner_gen);
        actividad=findViewById(R.id.spinner_act);
        meta=findViewById(R.id.spinner_meta);

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

        meta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                Seleccionmeta = meta.getItemAtPosition(i).toString();

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
        unpaciente.setEmail(email.getText().toString());
        unpaciente.setMetaa(Seleccionmeta);
        info.lista.add(unpaciente);

        
        Toast.makeText(this, "Elementos guardados", Toast.LENGTH_SHORT).show();

        
        nombre.setText("");
        apellido.setText("");
        edad.setText("");
        peso.setText("");
        altura.setText("");
        telefono.setText("");
        email.setText("");
        genero.setSelection(0); 
        actividad.setSelection(0);
        meta.setSelection(0);
    }
}