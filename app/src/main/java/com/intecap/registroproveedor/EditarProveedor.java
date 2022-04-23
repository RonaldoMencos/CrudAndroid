package com.intecap.registroproveedor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarProveedor extends Activity {
    FloatingActionButton btnEnviarEdicion, btnEnviarEliminacion;
    Button btnActualizar, btnRegresar;
    TextView campoNombreCompania,campoNombreContacto,campoCargoContacto,
            campoDireccion,campoCiudad,campoRegion,campoCodigoPostal,campoPais,
            campoTelefono,campoFax;
    Proveedor proveedor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
        btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setVisibility(View.INVISIBLE);
        btnEnviarEdicion = findViewById(R.id.btnEditar);
        btnEnviarEliminacion = findViewById(R.id.btnEliminar);
        btnActualizar.setVisibility(View.VISIBLE);
        btnEnviarEdicion.setVisibility(View.INVISIBLE);
        btnEnviarEliminacion.setVisibility(View.INVISIBLE);
        AccionesProveedor accionesProveedor =new AccionesProveedor(EditarProveedor.this);
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
        }else{
            Toast.makeText(EditarProveedor.this,"Error en cargar la info",Toast.LENGTH_LONG).show();
        }

        Integer finalId = id;
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!campoNombreCompania.getText().toString().equals("") && !campoNombreContacto.getText().toString().equals("") && !campoCargoContacto.getText().toString().equals("")
                        && !campoDireccion.getText().toString().equals("") && !campoCiudad.getText().toString().equals("") && !campoRegion.getText().toString().equals("")
                        && !campoCodigoPostal.getText().toString().equals("") && !campoPais.getText().toString().equals("") && !campoTelefono.getText().toString().equals("")
                        && !campoFax.getText().toString().equals("")){
                    boolean respuesta= accionesProveedor.editarProveedor(campoNombreCompania.getText().toString(),campoNombreContacto.getText().toString(),campoCargoContacto.getText().toString(),
                            campoDireccion.getText().toString(),campoCiudad.getText().toString(),campoRegion.getText().toString(),campoCodigoPostal.getText().toString(),
                            campoPais.getText().toString(),campoTelefono.getText().toString(),campoFax.getText().toString(),"", finalId);
                    if (respuesta){
                        Toast.makeText(EditarProveedor.this,"Actualizado correctamente",Toast.LENGTH_LONG).show();

                    }else{
                        Toast.makeText(EditarProveedor.this,"Error, intente de nuevo",Toast.LENGTH_LONG).show();
                    }
                    Intent i = new Intent(EditarProveedor.this, VerProveedor.class);
                    i.putExtra("id", finalId);
                    startActivity(i);
                }
            }
        });
    }
}
