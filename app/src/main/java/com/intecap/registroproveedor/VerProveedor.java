package com.intecap.registroproveedor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerProveedor extends AppCompatActivity {
    FloatingActionButton btnEnviarEdicion, btnEnviarEliminacion;
    Button btnActualizar;
    TextView campoNombreCompania,campoNombreContacto,campoCargoContacto,
            campoDireccion,campoCiudad,campoRegion,campoCodigoPostal,campoPais,
            campoTelefono,campoFax;
    Proveedor proveedor;
    ImageView vImagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_proveedor);
        campoNombreCompania = findViewById(R.id.txtVerNombreCompania);
        campoNombreContacto = findViewById(R.id.txtVerNombreContacto);
        campoCargoContacto = findViewById(R.id.txtVerCargoContacto);
        campoDireccion = findViewById(R.id.txtVerDireccion);
        campoCiudad = findViewById(R.id.txtVerCiudad);
        campoRegion = findViewById(R.id.txtVerRegion);
        campoCodigoPostal = findViewById(R.id.txtVerCodigoPostal);
        campoPais = findViewById(R.id.txtVerPais);
        campoTelefono = findViewById(R.id.txtTelefono);
        campoFax = findViewById(R.id.txtFax);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnActualizar.setVisibility(View.INVISIBLE);
        vImagen = findViewById(R.id.visorImagen2);

        AccionesProveedor accionesProveedor =new AccionesProveedor(VerProveedor.this);
        Integer id = 0;

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
                id= extras.getInt("id");
            }
        }
        proveedor = accionesProveedor.listarProveedorPorId(id);
        if (proveedor !=null){
            campoNombreCompania.setText(proveedor.getNombreCompania());
            campoNombreContacto.setText(proveedor.getNombreContacto());
            campoCargoContacto.setText(proveedor.getCargoContacto());
            campoDireccion.setText(proveedor.getDireccion());
            campoCiudad.setText(proveedor.getCiudad());
            campoRegion.setText(proveedor.getRegion());
            campoCodigoPostal.setText(proveedor.getCodigoPostal());
            campoPais.setText(proveedor.getPais());
            campoTelefono.setText(proveedor.getTelefono());
            campoFax.setText(proveedor.getFax());
            vImagen.setImageURI(Uri.parse(proveedor.getUbicacionFoto()));
        }else{
            Toast.makeText(VerProveedor.this,"Error en cargar la info",Toast.LENGTH_LONG).show();
        }
        campoNombreCompania.setKeyListener(null);
        campoNombreContacto.setKeyListener(null);
        campoCargoContacto.setKeyListener(null);
        campoDireccion.setKeyListener(null);
        campoCiudad.setKeyListener(null);
        campoRegion.setKeyListener(null);
        campoCodigoPostal.setKeyListener(null);
        campoPais.setKeyListener(null);
        campoTelefono.setKeyListener(null);
        campoFax.setKeyListener(null);

        btnEnviarEdicion = findViewById(R.id.btnEditar);
        Integer finalId = id;
        btnEnviarEdicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(VerProveedor.this, EditarProveedor.class);
                i.putExtra("id", finalId);
                startActivity(i);
            }
        });

        btnEnviarEliminacion = findViewById(R.id.btnEliminar);
        btnEnviarEliminacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mensaje = new AlertDialog.Builder(VerProveedor.this);
                mensaje.setMessage("¿Desea eliminar el proveedor?")
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (accionesProveedor.eliminarProveedor(finalId)){
                                    Toast.makeText(VerProveedor.this,"Proveedor eliminado con exito",Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(VerProveedor.this,MainActivity.class);
                                    startActivity(i);
                                }


                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();
            }
        });
    }

    public void regresar(View v) {
        Intent i = new Intent(VerProveedor.this, MainActivity.class);
        startActivity(i);
    }
}