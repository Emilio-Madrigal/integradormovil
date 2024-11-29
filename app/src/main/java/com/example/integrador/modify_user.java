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

public class modify_user extends AppCompatActivity {
    EditText edad, peso, altura, telefono, email;
    Spinner actividad, meta;
    Button modificar;
    Integer posicion = -1; // Se debe inicializar con el índice del paciente logueado.
    String Seleccionactividad, Seleccionmeta;

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

        modificar = findViewById(R.id.Modificar);
        edad = findViewById(R.id.edad);
        peso = findViewById(R.id.peso);
        altura = findViewById(R.id.altura);
        telefono = findViewById(R.id.telefono);
        email = findViewById(R.id.email);
        actividad = findViewById(R.id.spinner_act);
        meta = findViewById(R.id.spinner_meta);

        // Configuración de los spinners
        actividad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Seleccionactividad = actividad.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        meta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Seleccionmeta = meta.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        modificar.setOnClickListener(v -> actualizar());

        // Cargar los datos del paciente logueado
        mostrarpaciente();
    }

    private void actualizar() {
        paciente pacienteActual = info.listapaciente.get(posicion);

        // Actualizar los campos del paciente logueado
        pacienteActual.setEdad(edad.getText().toString());
        pacienteActual.setPeso(peso.getText().toString());
        pacienteActual.setAltura(altura.getText().toString());
        pacienteActual.setActividad(Seleccionactividad);
        pacienteActual.setTelefono(telefono.getText().toString());
        pacienteActual.setEmail(email.getText().toString());
        pacienteActual.setMetaa(Seleccionmeta);

        Toast.makeText(this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show();
    }

    private void mostrarpaciente() {
        paciente pacienteActual = info.listapaciente.get(posicion);

        // Mostrar los valores actuales en los campos correspondientes
        edad.setText(pacienteActual.getEdad());
        peso.setText(pacienteActual.getPeso());
        altura.setText(pacienteActual.getAltura());
        telefono.setText(pacienteActual.getTelefono());
        email.setText(pacienteActual.getEmail());

        // Configurar los valores iniciales de los spinners
        if (pacienteActual.getActividad() != null) {
            int actividadPosition = ((android.widget.ArrayAdapter<String>) actividad.getAdapter())
                    .getPosition(pacienteActual.getActividad());
            actividad.setSelection(actividadPosition);
        }

        if (pacienteActual.getMetaa() != null) {
            int metaPosition = ((android.widget.ArrayAdapter<String>) meta.getAdapter())
                    .getPosition(pacienteActual.getMetaa());
            meta.setSelection(metaPosition);
        }
    }
}
