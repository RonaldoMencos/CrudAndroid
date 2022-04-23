package com.intecap.registroproveedor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NuevoProveedor extends AppCompatActivity {

    EditText campoNombreCompania,campoNombreContacto,campoCargoContacto,
        campoDireccion,campoCiudad,campoRegion,campoCodigoPostal,campoPais,
        campoTelefono,campoFax;
    Button btnRegistrar;
    FloatingActionButton btnCam;
    ImageView vImagen;
    String ubicacionFoto;

    private static final int REQUEST_PERMISSION_CAMERA =20;
    private static final int REQUEST_IMAGE_CAMERA = 52;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_proveedor);
        campoNombreCompania = findViewById(R.id.campoNombreCompania);
        campoNombreContacto = findViewById(R.id.campoNombreContacto);
        campoCargoContacto = findViewById(R.id.campoCargoContacto);
        campoDireccion = findViewById(R.id.campoDireccion);
        campoCiudad = findViewById(R.id.campoCiudad);
        campoRegion = findViewById(R.id.campoRegion);
        campoCodigoPostal = findViewById(R.id.campoCodigoPostal);
        campoPais = findViewById(R.id.campoPais);
        campoTelefono = findViewById(R.id.campoTelefono);
        campoFax = findViewById(R.id.campoFax);
        btnRegistrar = findViewById(R.id.btnRegistrarCategoria);
        btnCam = findViewById(R.id.btnTomarFoto);
        vImagen = findViewById(R.id.visorImagen);

        btnCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                    if (ActivityCompat.checkSelfPermission(NuevoProveedor.this, Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED) {
                        capturarFoto();
                    } else {
                        ActivityCompat.requestPermissions(NuevoProveedor.this,new String[]{Manifest.permission.CAMERA},REQUEST_PERMISSION_CAMERA);
                    }
                } else {
                    capturarFoto();
                }
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccionesProveedor accionesProveedor = new AccionesProveedor(NuevoProveedor.this);
                long resspuesta = accionesProveedor.insertarProveedor(campoNombreCompania.getText().toString(),campoNombreContacto.getText().toString(),campoCargoContacto.getText().toString(),
                        campoDireccion.getText().toString(),campoCiudad.getText().toString(),campoRegion.getText().toString(),campoCodigoPostal.getText().toString(),
                        campoPais.getText().toString(),campoTelefono.getText().toString(),campoFax.getText().toString(),ubicacionFoto);
                if (resspuesta>0){
                    Toast.makeText(NuevoProveedor.this,"Proveedor registrado",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(NuevoProveedor.this,MainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(NuevoProveedor.this,"Error, intentar de nuevo",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(NuevoProveedor.this,MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CAMERA) {
            if (permissions.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                capturarFoto();
            } else {
                Toast.makeText(this,"Debe de brindar permisos.", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_IMAGE_CAMERA && resultCode == Activity.RESULT_OK){
            vImagen.setImageURI(Uri.parse(ubicacionFoto));
        }
    }

    private void capturarFoto() {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (i.resolveActivity(getPackageManager())!=null) {
            File imagenArchivo = null;

            try {
                imagenArchivo = crearArchivo();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (imagenArchivo != null) {
                Uri fotoUri = FileProvider.getUriForFile(NuevoProveedor.this,"com.intecap.registroproveedor",imagenArchivo);
                i.putExtra(MediaStore.EXTRA_OUTPUT,fotoUri);

                startActivityForResult(i,REQUEST_IMAGE_CAMERA);
            }
        }
    }

    private File crearArchivo() throws IOException {
        String formatoFecha = new SimpleDateFormat("yyyyMMdd_Hh-mm-ss", Locale.getDefault()).format(new Date());
        String nombreArchivo = "IMG_"+formatoFecha;
        File nuevaUbicacion = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagenTemp = File.createTempFile(nombreArchivo,".jpg",nuevaUbicacion);
        ubicacionFoto = imagenTemp.getAbsolutePath();
        return imagenTemp;
    }
}