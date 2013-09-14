package com.coffeeandcookies.cursoandroidutn;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class lay_login_correcto extends Activity
{
	String TAG = "Ciclodevida";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		Log.v(TAG, "onCreate - lay_login_correcto");
		setContentView(R.layout.lay_login_correcto);
		TextView textView_user = (TextView)findViewById(R.id.textView_user);
		String usuario = getIntent().getExtras().getString("user");
		textView_user.setText(usuario);
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onBackPressed() 
	{
		Log.v(TAG, "onBackPressed - lay_login_correcto");
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
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) 
	{
		Log.v(TAG, "onRestoreInstanceState - lay_login_correcto");
		super.onRestoreInstanceState(savedInstanceState);
	}


}
