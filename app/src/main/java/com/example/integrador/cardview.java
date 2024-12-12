package com.example.integrador;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import global.info;

public class cardview extends AppCompatActivity {


    Button llamarB;
    TextView nombre,apellido,edad,peso,altura,telefono,hora,fecha,genero,actividad;

    Toolbar toolbar;
    SharedPreferences archivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cardview);
      nombre=findViewById (R.id.nombrec);
      apellido=findViewById (R.id.apellidoc);
      edad=findViewById (R.id.edadc);
      peso=findViewById (R.id.pesoc);
      altura=findViewById (R.id.alturac);
      telefono=findViewById (R.id.telefonoc);
      hora=findViewById (R.id.citahorac);
      fecha=findViewById (R.id.citafechac);
      genero=findViewById (R.id.genc);
      actividad=findViewById (R.id.actc);
      toolbar=findViewById(R.id.toolbarCard);
        setSupportActionBar(toolbar);

        llamarB = findViewById(R.id.llamarc);

        llamarB.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                llamar();
            }
        });

        Intent intent = getIntent();
        int id = intent.getIntExtra("posicion", -1);
        Log.d("DEBUG_ID", "ID recibido: " + id);
        if (id != -1) {
            // cargar los datos del dentista desde el servidor
            cargarDatosDentista(id);
        } else {
            Toast.makeText(this, "Error al obtener el dentista seleccionado", Toast.LENGTH_SHORT).show();
        }
    }
    private void llamar() {
        Intent llamada = new Intent(Intent.ACTION_CALL);
        llamada.setData(Uri.parse(  "tel: "+ telefono.getText().toString()));
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CALL_PHONE
            }, 10);
        }
        startActivity(llamada);
    }
    private void cargarDatosDentista(int id) {
        String url = "http://10.0.2.2/bd/obtener_unpaciente.php?id=";
        url=url+id;
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("status").equals("success")) {
                                JSONObject dentista = response.getJSONObject("data");

                                nombre.setText(dentista.getString ("nombre"));
                                apellido.setText(dentista.getString ("apellido"));
                                genero.setText(dentista.getString ("genero"));
                                edad.setText(dentista.getString ("edad"));
                                peso.setText(dentista.getString ("peso"));
                                altura.setText(dentista.getString ("altura"));
                                telefono.setText(dentista.getString ("telefono"));
                                actividad.setText(dentista.getString ("actividad"));
                                hora.setText(dentista.getString ("hora_cita"));
                                fecha.setText(dentista.getString ("fecha_cita"));
                            } else {
                                Toast.makeText(cardview.this, "No se encontró información del dentista", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(cardview.this, "Error al procesar los datos", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(cardview.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(request);
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