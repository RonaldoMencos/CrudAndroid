package com.intecap.registroproveedor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AccionesProveedor extends UniversalConexion{

    Context pantallaProveedor;

    public AccionesProveedor(@Nullable Context context) {
        super(context);
        this.pantallaProveedor = context;
    }

    public Long insertarProveedor(String nombre_compania, String nombre_contacto, String cargo_contacto, String direccion,
                                  String ciudad, String region, String codigo_postal, String pais, String telefono,
                                  String fax, String ubicacionFoto) {
        long respuesta = 0;
        try {
            UniversalConexion conexion = new UniversalConexion(pantallaProveedor);
            SQLiteDatabase accionInsertar= conexion.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nombre_compania",nombre_compania);
            values.put("nombre_contacto",nombre_contacto);
            values.put("cargo_contacto",cargo_contacto);
            values.put("direccion",direccion);
            values.put("ciudad",ciudad);
            values.put("region",region);
            values.put("codigo_postal",codigo_postal);
            values.put("pais",pais);
            values.put("telefono",telefono);
            values.put("fax",fax);
            values.put("ubicacionFoto",ubicacionFoto);
            respuesta = accionInsertar.insert(TABLE_PROVEEDOR,null,values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    public ArrayList<Proveedor> listarProveedores(){
        UniversalConexion conexion = new UniversalConexion(pantallaProveedor);
        SQLiteDatabase accionListar = conexion.getWritableDatabase();

        ArrayList<Proveedor> listProveedors = new ArrayList<>();
        Proveedor proveedor =null;
        Cursor cursorProveedor= accionListar.rawQuery("SELECT * FROM "+TABLE_PROVEEDOR,null);

        if (cursorProveedor.moveToFirst()){
            do{
                proveedor = new Proveedor();
                proveedor.setId(cursorProveedor.getInt(0));
                proveedor.setNombreCompania(cursorProveedor.getString(1));
                proveedor.setNombreContacto(cursorProveedor.getString(2));
                proveedor.setCargoContacto(cursorProveedor.getString(3));
                proveedor.setDireccion(cursorProveedor.getString(4));
                proveedor.setCiudad(cursorProveedor.getString(5));
                proveedor.setRegion(cursorProveedor.getString(6));
                proveedor.setCodigoPostal(cursorProveedor.getString(7));
                proveedor.setPais(cursorProveedor.getString(8));
                proveedor.setTelefono(cursorProveedor.getString(9));
                proveedor.setFax(cursorProveedor.getString(10));
                proveedor.setUbicacionFoto(cursorProveedor.getString(11));
                listProveedors.add(proveedor);
            }while (cursorProveedor.moveToNext());
        }
        cursorProveedor.close();
        return listProveedors;
    }

    public Proveedor listarProveedorPorId(int id){
        UniversalConexion conexion = new UniversalConexion(pantallaProveedor);
        SQLiteDatabase accionListar = conexion.getWritableDatabase();

        Proveedor proveedor =null;
        Cursor cursorProveedor= accionListar.rawQuery("SELECT * FROM "+TABLE_PROVEEDOR + " WHERE id = " + id,null);

        if (cursorProveedor.moveToFirst()){
                proveedor = new Proveedor();
            proveedor = new Proveedor();
            proveedor.setId(cursorProveedor.getInt(0));
            proveedor.setNombreCompania(cursorProveedor.getString(1));
            proveedor.setNombreContacto(cursorProveedor.getString(2));
            proveedor.setCargoContacto(cursorProveedor.getString(3));
            proveedor.setDireccion(cursorProveedor.getString(4));
            proveedor.setCiudad(cursorProveedor.getString(5));
            proveedor.setRegion(cursorProveedor.getString(6));
            proveedor.setCodigoPostal(cursorProveedor.getString(7));
            proveedor.setPais(cursorProveedor.getString(8));
            proveedor.setTelefono(cursorProveedor.getString(9));
            proveedor.setFax(cursorProveedor.getString(10));
            proveedor.setUbicacionFoto(cursorProveedor.getString(11));
        }
        cursorProveedor.close();
        return proveedor;
    }

    public Boolean editarProveedor(String nombre_compania, String nombre_contacto, String cargo_contacto, String direccion,
                                   String ciudad, String region, String codigo_postal, String pais, String telefono,
                                   String fax, String ubicacionFoto, int id) {
        boolean respuesta = false;
        UniversalConexion conexion = new UniversalConexion(pantallaProveedor);
        SQLiteDatabase accionEditar = conexion.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre_compania",nombre_compania);
        values.put("nombre_contacto",nombre_contacto);
        values.put("cargo_contacto",cargo_contacto);
        values.put("direccion",direccion);
        values.put("ciudad",ciudad);
        values.put("region",region);
        values.put("codigo_postal",codigo_postal);
        values.put("pais",pais);
        values.put("telefono",telefono);
        values.put("fax",fax);
        values.put("ubicacionFoto",ubicacionFoto);
        try{
            accionEditar.update(TABLE_PROVEEDOR, values, "id=" + id,null);
            respuesta=true;
        }catch (Exception ex){
            ex.toString();
            respuesta=false;
        }finally {
            conexion.close();
        }
        return respuesta;
    }

    public boolean eliminarProveedor(int id){
        boolean eliminacionCorrecta=false;

        UniversalConexion conexion = new UniversalConexion(pantallaProveedor);
        SQLiteDatabase accionEliminar= conexion.getWritableDatabase();

        try{
            accionEliminar.execSQL("DELETE FROM "+TABLE_PROVEEDOR+" WHERE id="+id);
            eliminacionCorrecta=true;
        }catch (Exception ex){
            ex.toString();
            eliminacionCorrecta=false;
        }finally {
            conexion.close();
        }

        return eliminacionCorrecta;
    }
}
