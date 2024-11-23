package com.example.integrador;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText user,password;
    Button loggin;
    SharedPreferences archivo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        user=findViewById(R.id.user);
        password=findViewById(R.id.password);
        loggin=findViewById(R.id.loggin);
        archivo=this.getSharedPreferences("sesion", Context.MODE_PRIVATE);

        loggin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {loggin();}
        });
    }
    public void loggin() {
        String url = "http://10.0.2.2/bd/ingreso.php?usr=";
        url = url+user.getText().toString();
        url = url+"&pass=";
        url = url+password.getText().toString();

        JsonObjectRequest pet = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getInt("usr") != -1) {
                        Intent i = new Intent(MainActivity.this, ingreso_comida.class);
                        SharedPreferences.Editor editor = archivo.edit();
                        editor.putInt("id_usuario", response.getInt("usr"));
                        editor.commit();
                        startActivity(i);
                        finish();
                    } else {
                        user.setText("");
                        password.setText("");
                    }
                    Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this, volleyError.getMessage().toString(), Toast.LENGTH_SHORT).show();
                Log.d("yo", volleyError.getMessage());
            }
        });
        RequestQueue lanzarPeticion = Volley.newRequestQueue(this);
        lanzarPeticion.add(pet);
//        if(user.getText().toString().equals("emi")&&password.getText().toString().equals("123456")){
//            Intent in=new Intent(this,MainActivity.class);
//            SharedPreferences.Editor editor = archivo.edit();
//            editor.putString("usuaario","emi");
//            editor.putString("contra","123456");
//            editor.putBoolean("valido",true);
//
//            startActivity(in);
//            finish();
//        }
//        else{
//            user.setText("");
//            password.setText("");
//            Toast.makeText(this,"usuario o contrasena incorrecta",Toast.LENGTH_SHORT).show();
//        }


    }



}