package com.example.integrador;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import adaptador.adaptadorver;
import global.info;


public class ver extends AppCompatActivity {
    RecyclerView rv;
    Context context;
    Toolbar toolbar;
    SharedPreferences archivo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate (savedInstanceState);
        EdgeToEdge.enable (this);
        setContentView (R.layout.activity_ver);
        toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);

        rv = findViewById (R.id.recyclerview);// POR QUE FALLA EL XML INVESTIGA EMILIO
        adaptadorver av = new adaptadorver ();
        av.context = this;
        LinearLayoutManager llm = new LinearLayoutManager (this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager (llm);

        rv.setAdapter (av);


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
