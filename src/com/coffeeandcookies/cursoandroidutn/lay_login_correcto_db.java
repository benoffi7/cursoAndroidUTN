package com.coffeeandcookies.cursoandroidutn;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.coffeeandcookies.cursoandroidutn.daos.DAO_Usuarios;
import com.coffeeandcookies.cursoandroidutn.objetos.Usuario;

public class lay_login_correcto_db extends Activity
{
	private EditText edit_user_ok;
	private EditText edit_pass_uno;
	private EditText edit_pass_dos;
	private EditText edit_edad;
	private EditText edit_mail;
	private Button button_guardar;
	private Button button_borrar;
	private DAO_Usuarios dao;
	private Usuario oUsuario;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		setContentView(R.layout.lay_login_correcto);
		super.onCreate(savedInstanceState);
		dao = new DAO_Usuarios(getApplicationContext(), Configuracion.dbName, null, Configuracion.dbVersion);
		String nombre = getIntent().getExtras().getString("usuario");
		String pass = getIntent().getExtras().getString("pass");
		oUsuario = dao.recuperarDatosUsuario(nombre,pass);
		levantarXML();
		cargarUser();
		eventosBotones();
	}
	
	private void levantarXML()
	{
		edit_user_ok  = (EditText)findViewById(R.id.edit_user_ok);
		edit_pass_uno = (EditText)findViewById(R.id.edit_pass_uno);
		edit_pass_dos = (EditText)findViewById(R.id.edit_pass_dos);
		edit_edad     = (EditText)findViewById(R.id.edit_edad);
		edit_mail     = (EditText)findViewById(R.id.edit_mail);
		button_guardar= (Button)findViewById(R.id.button_guardar); 
		button_borrar = (Button)findViewById(R.id.button_borrar);
		
		edit_user_ok.setEnabled(false);
	}
	
	private void cargarUser()
	{
		edit_user_ok.setText(oUsuario.getUser());
		edit_pass_uno.setText(oUsuario.getPass());
		edit_pass_dos.setText(oUsuario.getPass());
		edit_edad.setText(""+oUsuario.getEdad());
		edit_mail.setText(oUsuario.getEmail());
	}
	
	private void eventosBotones() 
	{
		button_guardar.setOnClickListener(new OnClickListener() 
		{			
			@Override
			public void onClick(View v)
			{
				if (validarDatos())
				{
					guardarUser();
					Toast.makeText(lay_login_correcto_db.this, "Guardado!", Toast.LENGTH_SHORT).show();
					finish();
				}
				else
				{
					Toast.makeText(lay_login_correcto_db.this, "Completa todos los campos!", Toast.LENGTH_SHORT).show();
				}
					
			}
		});
		button_borrar.setOnClickListener(new OnClickListener()
		{			
			@Override
			public void onClick(View v)
			{
				borrarUser();
				finish();
			}
		});
	}
	
	private boolean validarDatos()
	{
		if (edit_user_ok.getText().toString().trim().length()==0)
			return false;
		if (edit_pass_uno.getText().toString().trim().length()==0)
			return false;
		if (edit_pass_dos.getText().toString().trim().length()==0)
			return false;
		if (edit_mail.getText().toString().trim().length()==0)
			return false;
		if (edit_edad.getText().toString().trim().length()==0)
			return false;
		if (!edit_pass_dos.getText().toString().equals(edit_pass_uno.getText().toString()))
			return false;
		return true;
	}
	
	private void borrarUser()
	{
		dao.borrarUser(oUsuario);
	}
	
	private void guardarUser()
	{
		oUsuario.setEdad(Integer.parseInt(edit_edad.getText().toString()));
		oUsuario.setEmail(edit_mail.getText().toString());
		oUsuario.setPass(edit_pass_uno.getText().toString());
		dao.actualizarUser(oUsuario);
	}
}
