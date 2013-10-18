package com.coffeeandcookies.cursoandroidutn;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

//http://mobile.tutsplus.com/tutorials/android/android-essentials-using-the-contact-picker/
/*
 * Intent intent = new Intent(Intent.ACTION_SEND);
					intent.setType("message/rfc822");
					intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"usuarios@ushuaiamovil.com.ar"});
					intent.putExtra(Intent.EXTRA_SUBJECT, "[ushuaiamovil] Contacto");
					intent.putExtra(Intent.EXTRA_TEXT, edit_consulta.getText().toString());
					startActivity(Intent.createChooser(intent, "Email"));
 */

public class lay_contactos extends Activity 
{
	private static final int CONTACT_PICKER_RESULT = 0;
	private static final String TAG = "Contactos";

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		setContentView(R.layout.lay_contactos);
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
											String email = "";
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
												EditText edit_mail = (EditText) findViewById(R.id.edit_mail);
												edit_mail.setText(email);
												if (edit_mail.length() == 0) 
												{
													Toast.makeText(this, "No tiene mail.",Toast.LENGTH_LONG).show();
												}
											}
											break;
			}
		}
	}
}
