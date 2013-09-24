package com.coffeeandcookies.cursoandroidutn;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class lay_login_correcto extends Activity
{
	String TAG = "Ciclodevida";
	private EditText edit_user_ok;
	private EditText edit_pass_uno;
	private EditText edit_pass_dos;
	private EditText edit_edad;
	private EditText edit_mail;
	private Button button_guardar;
	private Button button_borrar;
	private SharedPreferences mSharedPreferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		Log.v(TAG, "onCreate - lay_login_correcto");
		setContentView(R.layout.lay_login_correcto);
		mSharedPreferences = getApplicationContext().getSharedPreferences(Configuracion.misprefs, 0);
		//Ya no tiene sentido porque el nombre lo estoy guardando en las prefs
		//String usuario = getIntent().getExtras().getString(Configuracion.user);
		super.onCreate(savedInstanceState);
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
	}
	
	private void cargarUser()
	{
		String nombre = mSharedPreferences.getString(Configuracion.user, "");
		String pass = mSharedPreferences.getString(Configuracion.pass, "");
		String edad = mSharedPreferences.getString(Configuracion.edad, "");
		String mail = mSharedPreferences.getString(Configuracion.mail, "");
		
		edit_user_ok.setText(nombre);
		edit_pass_uno.setText(pass);
		edit_pass_dos.setText(pass);
		edit_edad.setText(edad);
		edit_mail.setText(mail);
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
					Toast.makeText(lay_login_correcto.this, "Guardado!", Toast.LENGTH_SHORT).show();
					finish();
				}
				else
				{
					Toast.makeText(lay_login_correcto.this, "Completa todos los campos!", Toast.LENGTH_SHORT).show();
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
		SharedPreferences.Editor editor = mSharedPreferences.edit();
		editor.putString(Configuracion.user, "");
		editor.putString(Configuracion.pass, "");
		editor.putString(Configuracion.edad, "");
		editor.putString(Configuracion.mail, "");
		editor.commit();
	}
	
	private void guardarUser()
	{
		SharedPreferences.Editor editor = mSharedPreferences.edit();
		editor.putString(Configuracion.user, edit_user_ok.getText().toString());
		editor.putString(Configuracion.pass, edit_pass_uno.getText().toString());
		editor.putString(Configuracion.edad, edit_edad.getText().toString());
		editor.putString(Configuracion.mail, edit_mail.getText().toString());
		editor.commit();
	}
	
	
	@Override
	public void onBackPressed() 
	{
		Log.v(TAG, "onBackPressed - lay_login_correcto");
		finish();
		super.onBackPressed();
	}
	
	@Override
	protected void onResume() 
	{
		Log.v(TAG, "onResume - lay_login_correcto");
		super.onResume();
	}
	
	@Override
	protected void onDestroy() 
	{
		Log.v(TAG, "onDestroy - lay_login_correcto");
		super.onDestroy();
	}
	
	@Override
	protected void onPause() 
	{
		Log.v(TAG, "onPause - lay_login_correcto");
		super.onPause();
	}
	
	@Override
	protected void onStart() 
	{
		Log.v(TAG, "onStart - lay_login_correcto");
		super.onStart();
	}
	
	@Override
	protected void onRestart() 
	{
		Log.v(TAG, "onRestart - lay_login_correcto");
		super.onRestart();
	}
	
	@Override
	protected void onStop() 
	{
		Log.v(TAG, "onStop - lay_login_correcto");
		super.onStop();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		Log.v(TAG, "onSaveInstanceState - lay_login_correcto");
		super.onSaveInstanceState(outState);
		outState.putBoolean("valor", true);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) 
	{
		Log.v(TAG, "onRestoreInstanceState - lay_login_correcto");
		super.onRestoreInstanceState(savedInstanceState);
		savedInstanceState.getBoolean("valor");
	}


}
