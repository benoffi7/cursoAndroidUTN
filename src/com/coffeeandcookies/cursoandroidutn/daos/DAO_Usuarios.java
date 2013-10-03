package com.coffeeandcookies.cursoandroidutn.daos;

import java.util.ArrayList;

import android.R.bool;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.coffeeandcookies.cursoandroidutn.objetos.Usuario;

public class DAO_Usuarios extends SQLiteOpenHelper
{ 
	public DAO_Usuarios(Context contexto, String nombre, CursorFactory factory, int version) 
	{
	      super(contexto, nombre, factory, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		db.execSQL("CREATE TABLE Usuarios (nombre TEXT,pass TEXT)");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) 
	{
	     
	}
	
	public void insertarDatos(Usuario oUsuario)
	{
		 SQLiteDatabase baseDatos = getWritableDatabase();
		 baseDatos.execSQL("INSERT INTO Usuarios (nombre,pass) VALUES ('"+oUsuario.getUser()+"','"+oUsuario.getUser()+"')");
		 baseDatos.close(); 
	}

	public ArrayList<Usuario> recuperarDatos()
	{
		 SQLiteDatabase baseDatos = getWritableDatabase(); 
		 String sql = "SELECT * FROM Usuarios"; 
		 Cursor cursor = baseDatos.rawQuery(sql, null); 
		 ArrayList<Usuario >usuarios=new ArrayList<Usuario>();  
		 while (cursor.moveToNext()) 
		 { 
			 Usuario oUsuario=new Usuario(); 
			 oUsuario.setUser(cursor.getString(0)); 
			 oUsuario.setPass(cursor.getString(1)); 
		     usuarios.add(oUsuario);            
		 }       
		 return usuarios;
	}
	
	/**
	 * 
	 * @param oUsuario
	 * @return  0 si el usuario existe y coincide pass <br>
	 *         -1 si el usuario no existe <br> 
	 *         -2 si el usuario existe pero no coincide pass <br>
	 */
	public int validarUser(Usuario oUsuario)
	{
		int retorno = -3;
		SQLiteDatabase baseDatos = getWritableDatabase();
		String sql = "SELECT * FROM Usuarios where nombre = ' "+oUsuario.getUser()+" ' "; 
		Cursor cursor = baseDatos.rawQuery(sql, null);
		if (cursor.getCount() == 0)
		{
			retorno = -1;
		}
		else
		{
			sql = "SELECT * FROM Usuarios where nombre = ' "+oUsuario.getUser()+" ' and pass = ' "+oUsuario.getPass()+" ' "; 
			cursor = baseDatos.rawQuery(sql, null);
			if (cursor.getCount() == 1)
			{
				retorno = 0;
			}
			else
			{
				retorno =-2;
			}
			
		}
		baseDatos.close(); 
		cursor.close();    
		return retorno;		
	}
}
