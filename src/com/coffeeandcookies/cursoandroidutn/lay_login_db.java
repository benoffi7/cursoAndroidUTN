package com.coffeeandcookies.cursoandroidutn;

import com.coffeeandcookies.cursoandroidutn.daos.DAO_Usuarios;
import com.coffeeandcookies.cursoandroidutn.objetos.Usuario;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class lay_login_db extends Activity
{
	private EditText edit_user;
	private EditText edit_pass;
	private Button button_login;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		setContentView(R.layout.lay_login);
		levantarXML();		
		asignarEventos();
		super.onCreate(savedInstanceState);
	}
	
	
	private void guardarUser()
	{
		Usuario oUsuario = new Usuario();
		oUsuario.setUser(edit_user.getText().toString());
		oUsuario.setPass(edit_pass.getText().toString());
		DAO_Usuarios miDAO = new DAO_Usuarios(getApplicationContext(), Configuracion.dbName, null, Configuracion.dbVersion);
		miDAO.insertarDatos(oUsuario);
	}
	
	private void asignarEventos() 
	{
		button_login.setOnClickListener(new OnClickListener() 
		{			
			@Override
			public void onClick(View v) 
			{
				String validacion = validarDatos();
				if (validacion.length()==0)
				{
					guardarUser();
					Toast.makeText(getApplicationContext(), "Validacion OK", Toast.LENGTH_LONG).show();
					Intent intento = new Intent(lay_login_db.this,lay_login_correcto.class);
					startActivity(intento);
				}
				else
				{
					Toast.makeText(getApplicationContext(), validacion, Toast.LENGTH_LONG).show();
				}
			}
		});
	}
	
	private String validarDatos()
	{
		if (edit_pass.getText().toString().length()==0)
		{
			return "Pass vacio";
		}
		if (edit_user.getText().toString().length()==0)
		{
			return "User vacio";
		}
		
		Usuario oUsuario = new Usuario();
		oUsuario.setUser(edit_user.getText().toString());
		oUsuario.setPass(edit_pass.getText().toString());
		
		//comentarios
		//modularizacion
				
		DAO_Usuarios miDAO = new DAO_Usuarios(getApplicationContext(), Configuracion.dbName, null, Configuracion.dbVersion);
		
		if (miDAO.validarUser(oUsuario) == -2)
		{
			return  "Nombre/Password no coincide";
		}	
		
		return "";
		
		//http://devopsreactions.tumblr.com/page/17
	}

	private void levantarXML() 
	{
		edit_user = (EditText)findViewById(R.id.edit_user);
		edit_pass = (EditText)findViewById(R.id.edit_pass);
		button_login = (Button)findViewById(R.id.button_login);
	}
}
