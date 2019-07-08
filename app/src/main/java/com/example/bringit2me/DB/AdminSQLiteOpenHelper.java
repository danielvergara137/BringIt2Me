package com.example.bringit2me.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String CREATE_TABLE_USUARIO = "CREATE TABLE usuario(username text PRIMARY KEY, " +
            "nombre text, password text, correo text, telefono int, rut text, sexo text)";

    private static final String CREATE_TABLE_PEDIDO = "CREATE TABLE pedido(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            "fecha text, origen text, destino int, precio int, estado text, usuario text)";

    private static final String CREATE_TABLE_PRODUCTO = "CREATE TABLE producto(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "nombre text, descripcion text, idpedido int)";

    private static final String CREATE_TABLE_ENTREGA = "CREATE TABLE entrega(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            "estado text, idpedido text, usuario text)";

    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USUARIO);
        db.execSQL(CREATE_TABLE_PEDIDO);
        db.execSQL(CREATE_TABLE_ENTREGA);
        db.execSQL(CREATE_TABLE_PRODUCTO);
        db.execSQL("insert into usuario values('usuario','Juan Soto','pass','juansoto@gmail.com',987654321,'17777777-7','masculino')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
