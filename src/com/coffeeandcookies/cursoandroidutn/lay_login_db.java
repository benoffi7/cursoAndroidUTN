package com.coffeeandcookies.cursoandroidutn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.coffeeandcookies.cursoandroidutn.daos.DAO_Usuarios;
import com.coffeeandcookies.cursoandroidutn.objetos.Usuario;

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
	
	@Override
	protected void onResume() 
	{
		edit_user.setText("");
		edit_pass.setText("");
		super.onResume();
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
				switch (validarDatos()) 
				{
					case -3:
						Toast.makeText(getApplicationContext(), "Complete los datos requeridos", Toast.LENGTH_LONG).show();
						break;
					case -2:
						Toast.makeText(getApplicationContext(), "Usuario existe pero contraseña incorrecta", Toast.LENGTH_LONG).show();
						break;
					case -1:
						Toast.makeText(getApplicationContext(), "Usuario no existe - Se guardara en la base", Toast.LENGTH_LONG).show();
						guardarUser();
						Toast.makeText(getApplicationContext(), "Validacion OK", Toast.LENGTH_LONG).show();
						Intent intento = new Intent(lay_login_db.this,lay_login_correcto_db.class);
						intento.putExtra("usuario", edit_user.getText().toString());
						intento.putExtra("pass", edit_pass.getText().toString());
						startActivity(intento);
						break;
					case 0:
						Toast.makeText(getApplicationContext(), "Validacion OK", Toast.LENGTH_LONG).show();
						Intent intento1 = new Intent(lay_login_db.this,lay_login_correcto_db.class);
						intento1.putExtra("usuario", edit_user.getText().toString());
						intento1.putExtra("pass", edit_pass.getText().toString());
						startActivity(intento1);
						break;
					default:
						break;
				}
			}
		});
	}
	
	/**
	 * 
	 * @return 	0 si el usuario existe y coincide pass <br> 
			   -1 si el usuario no existe <br>
			   -2 si el usuario existe pero no coincide pass <br> 
			   -3 campos vacios <br>
	 */
	private int validarDatos()
	{
		if (edit_pass.getText().toString().length()==0)
		{
			return -3;
		}
		if (edit_user.getText().toString().length()==0)
		{
			return -3;
		}
		
		Usuario oUsuario = new Usuario();
		oUsuario.setUser(edit_user.getText().toString());
		oUsuario.setPass(edit_pass.getText().toString());
		
		//comentarios
		//modularizacion
		
		//bundle y flags
		//como instanciar una dao
		//polo tecnologico y streetview
				
		DAO_Usuarios miDAO = new DAO_Usuarios(getApplicationContext(), Configuracion.dbName, null, Configuracion.dbVersion);
		
		return miDAO.validarUser(oUsuario);
		
		//http://devopsreactions.tumblr.com/page/17
	}

	private void levantarXML() 
	{
		edit_user = (EditText)findViewById(R.id.edit_user);
		edit_pass = (EditText)findViewById(R.id.edit_pass);
		button_login = (Button)findViewById(R.id.button_login);
	}
}
