package com.example.integrador;


import static global.info.listaComida;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import adaptador.adaptadorelim;
import pojo.comida;

public class ver2 extends AppCompatActivity {

    RecyclerView rv2;
    Context context;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver2);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rv2=findViewById(R.id.recyclerviewE);
        adaptadorelim av = new adaptadorelim();
        av.context=this;
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv2.setLayoutManager(llm);
        rv2.setAdapter(av);

    }
    public void eliminar(View view) {

        List<comida> Trashcan = new ArrayList<> ();

        for (int i = 0; i < listaComida.size(); i++) {
            comida item = listaComida.get(i);
            if (item.isChecked()) {
                Trashcan.add(item);
            }
        }
        listaComida.removeAll(Trashcan);
        rv2.getAdapter().notifyDataSetChanged();
    }
}