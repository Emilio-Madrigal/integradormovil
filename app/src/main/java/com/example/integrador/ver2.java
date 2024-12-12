package com.example.integrador;



import static global.info.listapaciente;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adaptador.adaptadorelim;
import global.info;
import pojo.paciente;

public class ver2 extends AppCompatActivity {

    RecyclerView rv2;
    Context context;
    Toolbar toolbar;
    SharedPreferences archivo;
    adaptadorelim adapter;
    List<paciente> listapa = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver2);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rv2 = findViewById(R.id.recyclerviewE);

        adapter = new adaptadorelim(this, listapa);
        rv2.setLayoutManager(new LinearLayoutManager(this));
        rv2.setAdapter(adapter);
        mostrar();

    }
    public void EliminarB (View view) {
    List<paciente> itemsParaEliminar = new ArrayList<>();
    List<Integer> posicionesSeleccionadas = new ArrayList<>();

        for (int i = 0; i < listapa.size(); i++) {
        paciente item = listapa.get(i);
        if (item.isChecked()) {
            itemsParaEliminar.add(item);
            posicionesSeleccionadas.add(i);
        }
    }
        if (!itemsParaEliminar.isEmpty()) {
        for (paciente item : itemsParaEliminar) {
            // eliminarDentista(item); // Asegúrate de implementar este método correctamente
        }
        listapa.removeAll(itemsParaEliminar);
        adapter.notifyDataSetChanged();
        Toast.makeText(ver2.this, "Elementos eliminados exitosamente", Toast.LENGTH_SHORT).show();

        // Mostrar las posiciones de los elementos eliminados
        for (int posicion : posicionesSeleccionadas) {
            posicion++;
            Toast.makeText(ver2.this, "Elemento eliminado en posición: " + posicion, Toast.LENGTH_SHORT).show();
            eliminar(posicion);
        }
    } else {
        Toast.makeText(ver2.this, "No has seleccionado ningún elemento para eliminar", Toast.LENGTH_SHORT).show();
    }
    }

    public void eliminar(int id) {
     String url = "http://10.0.2.2/bd/eliminar.php?id="+id;
Log.d("id eliminar","es: "+id);
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        String status = response.getString("status");
                        if (!status.equals("success")) {
                            String message = response.getString("message");
                            Toast.makeText(ver2.this, message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(ver2.this, "Error al procesar la respuesta", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Log.e("VolleyError", "Error en la solicitud: " + error.getMessage());
                    Toast.makeText(ver2.this, "Error de red: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
    public void mostrar(){
        String url = "http://10.0.2.2/bd/obtener_pacientes.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        String status = response.getString("status");
                        if (status.equals("success")) {
                            JSONArray dataArray = response.getJSONArray("data");

                            listapa.clear();

                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject dentistaObj = dataArray.getJSONObject(i);

                                paciente estoyarto = new paciente ();
                                estoyarto.setNombre (dentistaObj.getString("nombre"));
                                estoyarto.setApellido (dentistaObj.getString("apellido"));

                                listapa.add(estoyarto);
                            }

                            adapter.notifyDataSetChanged();
                        } else {
                            String message = response.getString("message");
                            Toast.makeText(ver2.this, message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(ver2.this, "Error al procesar los datos", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Log.e("VolleyError", "Error en la solicitud: " + error.getMessage());
                    Toast.makeText(ver2.this, "Error de red: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);

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