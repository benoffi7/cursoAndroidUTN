package com.example.cursoandroidutnnivel1.login;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cursoandroidutnnivel1.R;

public class lay_login extends Activity
{
	 EditText edit_user;
	EditText edit_pass;
	 Button button_login;

	String TAG = "Ciclodevida";
	String password = "velez";
	
	int cantidaddelogin = 3;
	int aux = 0;
	ArrayList<String> usuarios = new ArrayList<String>();
	ArrayList<String> passwords= new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		Log.d(TAG, "onCreate - lay_login");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_login);
		levantarXML();
		asignarEventos();
		initData();
		
		String variable = edit_user.getText().toString();
		boolean coincidencia = variable.equals("pepe");
		int cantidad = variable.length();
	}	
	
	@Override
	protected void onResume()
	{
		edit_user.setText("");
		edit_pass.setText("");
		super.onResume();
	}
	
	private void initData()
	{
		usuarios.add("gonzalo");
		usuarios.add("natalia");
		usuarios.add("noelia");
		usuarios.add("luciana");
		usuarios.add("candela");
		
		passwords.add("app01");
		passwords.add("app02");
		passwords.add("app03");
		passwords.add("app04");
		passwords.add("app05");
		
		String nombre = getResources().getString(R.string.app_name);
	}

	private void levantarXML()
	{
		edit_user = (EditText) findViewById(R.id.edit_user);
		edit_pass = (EditText) findViewById(R.id.edit_pass);
		button_login = (Button) findViewById(R.id.button_login);
	}

	private void asignarEventos()
	{
//		int x= 5;
//		for (;x<usuarios.size();x++)
//		{
//			
//		}
		
		button_login.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String validacion = validarDatos();
				//validacion = validarUsuarios();
				if (validacion.length() == 0)
				{
					Toast.makeText(getApplicationContext(), "Validacion OK", Toast.LENGTH_SHORT).show();
					Intent intento = new Intent(lay_login.this, lay_login_correcto.class);
					intento.putExtra("pos", edit_user.getText().toString());
					startActivity(intento);
				}
				else //sino
				{
					Toast.makeText(getApplicationContext(), validacion, Toast.LENGTH_LONG).show();
					aux = aux + 1;
					if (aux == cantidaddelogin )
					{
						finish();
					}
				}
			}
		});
	}

	protected String validarUsuarios()
	{
		
		for (int i = 0;i<usuarios.size();i++)
		{
			if (usuarios.get(i).equals(edit_user.getText().toString()))
			{
				String password = passwords.get(i);
				if (!password.equals(edit_pass.getText().toString()))
				{
					return "Clave incorrecta"; 
				}
				else
				{
					return "";
				}
			}
		}
		return "No se encontro el usuario ";
	}

	private String validarDatos()
	{
		if (edit_pass.getText().toString().length() == 0)
		{
			return "Pass vacio";
		}
		if (edit_user.getText().toString().length() == 0)
		{
			return "User vacio";
		}
		if (!edit_pass.getText().toString().equals(password))
		{
			return "Contraseña incorrecta";
		}
		return "";
	}

	

	@Override
	protected void onPause()
	{
		Log.d(TAG, "onPause - lay_login");
		super.onPause();
	}

	@Override
	protected void onDestroy()
	{
		Log.d(TAG, "onDestroy - lay_login");
		super.onDestroy();
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
}
