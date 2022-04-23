package com.intecap.registroproveedor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView listaCategoria;
    ArrayList<Proveedor> listaArrayProveedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaCategoria=findViewById(R.id.listadoProveedor);
        listaCategoria.setLayoutManager(new LinearLayoutManager(this));

        AccionesProveedor accionesProveedor = new AccionesProveedor(this);

        listaArrayProveedor = new ArrayList<>();
        AdaptadorProveedor adaptador = new AdaptadorProveedor(accionesProveedor.listarProveedores());
        listaCategoria.setAdapter(adaptador);
    }

    public void nuevoProveedor(View v) {
        Intent i = new Intent(MainActivity.this, NuevoProveedor.class);
        startActivity(i);
    }
}