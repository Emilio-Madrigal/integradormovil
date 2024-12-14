package com.example.integrador;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import adaptador.adaptadorver;
import global.info;
import pojo.paciente;

public class ver extends AppCompatActivity {
    RecyclerView rv;
    Toolbar toolbar;
    SharedPreferences archivo;
    List<paciente> dentistasList = new ArrayList<>();
    adaptadorver adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver);
        archivo = this.getSharedPreferences("sesion", Context.MODE_PRIVATE);
        archivo = this.getSharedPreferences("sesion", Context.MODE_PRIVATE);
        rv = findViewById(R.id.recyclerview);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configurar RecyclerView
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new adaptadorver(this, dentistasList);
        rv.setAdapter(adapter);

        // Cargar datos
        cargarDatos();
    }

    private void cargarDatos() {
        String url = "http://10.0.2.2/bd/obtener_pacientes.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String status = response.getString("status");
                            if (status.equals("success")) {
                                JSONArray dataArray = response.getJSONArray("data");
                                dentistasList.clear();

                                for (int i = 0; i < dataArray.length(); i++) {
                                    JSONObject dentistaObj = dataArray.getJSONObject(i);
                                    paciente dentistaItem = new paciente();
                                    dentistaItem.setNombre(dentistaObj.getString("nombre"));
                                    dentistaItem.setApellido(dentistaObj.getString("apellido"));

                                    dentistasList.add(dentistaItem);
                                }
                                adapter.notifyDataSetChanged();
                            } else {
                                String message = response.getString("message");
                                Toast.makeText(ver.this, message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("JSONException", "Error al procesar el JSON: " + e.getMessage());
                            Toast.makeText(ver.this, "Error al procesar los datos", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VolleyError", "Error en la solicitud: " + error.getMessage());
                        Toast.makeText(ver.this, "Error de red: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.read) {

                Intent cambio8 = new Intent(this, ver.class);
                startActivity(cambio8);
        }

        if (item.getItemId() == R.id.create) {
            Intent cambio8 = new Intent(this, user_info.class);
            startActivity(cambio8);
        }

        if (item.getItemId() == R.id.update) {
            Intent cambio8 = new Intent(this, modify_user.class);
            startActivity(cambio8);
        }
        if (item.getItemId() == R.id.delete) {
            Intent cambio8 = new Intent(this, ver2.class);
            startActivity(cambio8);
        }
        if (item.getItemId() == R.id.logout) {
            if (archivo.contains("id_usuario")) {
                SharedPreferences.Editor editor = archivo.edit();
                editor.remove("id_usuario");
                editor.apply();
                Intent x = new Intent(this, MainActivity.class);
                startActivity(x);
                finish();
            }

        }
        return super.onOptionsItemSelected(item);
    }
}
