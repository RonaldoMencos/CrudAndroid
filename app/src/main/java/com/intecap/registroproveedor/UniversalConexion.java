package com.intecap.registroproveedor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UniversalConexion extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dbProveedor.db";
    public static final String TABLE_PROVEEDOR = "proveedor";

    public UniversalConexion(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_PROVEEDOR +"(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre_compania TEXT," +
                "nombre_contacto TEXT," +
                "cargo_contacto TEXT," +
                "direccion TEXT," +
                "ciudad TEXT," +
                "region TEXT," +
                "codigo_postal TEXT," +
                "pais TEXT," +
                "telefono TEXT," +
                "fax TEXT," +
                "ubicacionFoto TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_PROVEEDOR);
        onCreate(db);
    }
}
