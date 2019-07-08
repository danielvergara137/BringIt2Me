package com.example.bringit2me.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bringit2me.ui.login.Usuario;

public class DBQueries {

    public static boolean LoginUsuario(String username, String password, Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String query = "SELECT password FROM usuario WHERE username = '" + username +"'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            if (cursor.getString(0).compareTo(password)==0) {
                db.close();
                return true;
            }
        }
        db.close();
        return false;
    }

    public static boolean isUsuarioRegistrado(String username, Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String query = "SELECT * FROM usuario WHERE username = '" + username +"'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) return true;
        else return false;
    }

    public static Usuario getUsuario(String username, Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String query = "SELECT username, nombre, correo, telefono, rut, sexo FROM usuario WHERE username = '" + username + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            Usuario usuario = new Usuario(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5));
            db.close();
            return usuario;
        }
        db.close();
        return null;
    }

    public static boolean solicitarPedido(String fecha, String origen, String destino, int precio, String usuario, Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String query = "INSERT INTO pedido(fecha, origen, destino, precio, estado, usuario) VALUES ('" + fecha + "', '" + origen + "', '" + destino + "', '" + precio + "', 'pendiente', '" + usuario + "')";
        db.execSQL(query);
        db.close();
        return true;
    }
}
