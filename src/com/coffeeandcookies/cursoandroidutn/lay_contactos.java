package com.coffeeandcookies.cursoandroidutn;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class lay_contactos extends Activity 
{
	private static final int CONTACT_PICKER_RESULT = 0;
	private static final String TAG = "Contactos";
	private String email;
	private String number;
	private EditText edit_mail;
	private EditText edit_telefono;
	private EditText edit_contenido;
	private Button button_llamar;
	private Button button_enviar_mail;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		setContentView(R.layout.lay_contactos);
		
		//Levanto XML
		edit_mail = (EditText) findViewById(R.id.edit_mail);
		edit_telefono = (EditText) findViewById(R.id.edit_telefono);
		edit_contenido = (EditText) findViewById(R.id.edit_contenido);
		button_llamar = (Button)findViewById(R.id.button_llamar);
		button_enviar_mail = (Button)findViewById(R.id.button_enviar_mail);
		
		//Eventos de los botones
		button_llamar.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// Mensaje de texto
				
				/*
				try
				{
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(number, null, edit_contenido.getText().toString(), null,null);
					Toast.makeText(getApplicationContext(), "¡SMS Enviado!", Toast.LENGTH_LONG).show();
				}
				catch (Exception e)
				{
					Toast.makeText(getApplicationContext(), "¡SMS falló!", Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}*/
				
				//llamar
				
				try
				{
					Intent intent = new Intent(Intent.ACTION_CALL);
					intent.setData(Uri.parse("tel:" + number));
					startActivity(intent);
				}
				catch (Exception ex)
				{
					Toast.makeText(getApplicationContext(), "Fallo llamada", Toast.LENGTH_LONG).show();
					ex.printStackTrace();
				}
				
			}
		});
		
		button_enviar_mail.setOnClickListener(new OnClickListener()
		{			
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("message/rfc822");
				intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@ushuaiamovil.com.ar"});
				intent.putExtra(Intent.EXTRA_SUBJECT, "[ushuaiamovil] Contacto");
				intent.putExtra(Intent.EXTRA_TEXT, edit_contenido.getText().toString());
				startActivity(Intent.createChooser(intent, "Email"));
				
			}
		});
		
		//lanzo el picker de contactos
		Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI);  
	    startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);  
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode == RESULT_OK)
		{
			switch (requestCode) 
			{
				case CONTACT_PICKER_RESULT:
											Cursor cursor = null;
											email = "";
											try 
											{
												Uri result = data.getData();
												Log.v(TAG,"Informacion de contacto: " + result.toString());
												String id = result.getLastPathSegment();
												
												cursor = getContentResolver().query(Email.CONTENT_URI,null, Email.CONTACT_ID + "=?", new String[] { id },null);
												int emailIdx = cursor.getColumnIndex(Email.DATA);
												if (cursor.moveToFirst()) 
												{
													email = cursor.getString(emailIdx);
													Log.v(TAG, "Tengo email: " + email);
												} 
												else
												{
													Log.w(TAG, "No hay resultados");
												}
												
												 Cursor phones = getContentResolver().query(Phone.CONTENT_URI, null, Phone.CONTACT_ID + " = " + id, null, null);
											     while (phones.moveToNext())
											     {
											    	 number = phones.getString(phones.getColumnIndex(Phone.NUMBER));
											    	 Log.v(TAG, "Tengo telefono: " + number);											               
											     }
											     phones.close();
											} 
											catch (Exception e)
											{
												Log.e(TAG, "Fallo", e);
											} 
											finally 
											{
												if (cursor != null)
												{
													cursor.close();
												}
												edit_mail.setText(email);
												edit_telefono.setText(number);
											}
											break;
			}
		}
	}
}
