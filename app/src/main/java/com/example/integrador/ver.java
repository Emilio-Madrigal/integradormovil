package com.example.integrador;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import adaptador.adaptadorver;


public class ver extends AppCompatActivity {
    RecyclerView rv;
    Context context;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver);

        rv=findViewById(R.id.recyclerview);// POR QUE FALLA EL XML INVESTIGA EMILIO
        adaptadorver av = new adaptadorver();
        av.context=this;
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(llm);

        rv.setAdapter(av);


    }
}