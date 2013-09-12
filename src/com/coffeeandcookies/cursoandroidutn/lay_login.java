package com.coffeeandcookies.cursoandroidutn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class lay_login extends Activity
{
	private EditText edit_user;
	private EditText edit_pass;
	private Button button_login;
	
	String TAG = "Ciclodevida";
	String password = "Velez";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		Log.d(TAG, "onCreate");
		setContentView(R.layout.lay_login);
		levantarXML();
		asignarEventos();
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void onResume() 
	{
		Log.d(TAG, "onResume - lay_login");
		super.onResume();
	}
	
	@Override
	protected void onDestroy() 
	{
		Log.d(TAG, "onDestroy - lay_login");
		super.onDestroy();
	}
	
	@Override
	protected void onPause() 
	{
		Log.d(TAG, "onPause - lay_login");
		super.onPause();
	}
	
	@Override
	protected void onStart() 
	{
		Log.d(TAG, "onStart - lay_login");
		super.onStart();
	}
	
	@Override
	protected void onRestart() 
	{
		Log.d(TAG, "onRestart - lay_login");
		super.onRestart();
	}
	
	@Override
	protected void onStop() 
	{
		Log.d(TAG, "onStop - lay_login");
		super.onStop();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		Log.d(TAG, "onSaveInstanceState - lay_login");
		super.onSaveInstanceState(outState);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) 
	{
		Log.d(TAG, "onRestoreInstanceState - lay_login");
		super.onRestoreInstanceState(savedInstanceState);
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
					Toast.makeText(getApplicationContext(), "Validacion OK", Toast.LENGTH_LONG).show();
					Intent intento = new Intent(lay_login.this,lay_login_correcto.class);
					intento.putExtra("user", edit_user.getText().toString());
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
		if (!edit_pass.getText().toString().equals(password))
		{
			return "Contraseña incorrecta";
		}
		return "";		
	}

	private void levantarXML() 
	{
		edit_user = (EditText)findViewById(R.id.edit_user);
		edit_pass = (EditText)findViewById(R.id.edit_pass);
		button_login = (Button)findViewById(R.id.button_login);
	}
}
