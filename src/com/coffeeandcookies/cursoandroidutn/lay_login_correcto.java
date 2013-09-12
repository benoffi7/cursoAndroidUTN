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
		setContentView(R.layout.lay_login_correcto);
		TextView textView_user = (TextView)findViewById(R.id.textView_user);
		String usuario = getIntent().getExtras().getString("user");
		textView_user.setText(usuario);
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onBackPressed() 
	{
		Log.d(TAG, "onBackPressed - lay_login_correcto");
		super.onBackPressed();
	}
	
	@Override
	protected void onResume() 
	{
		Log.d(TAG, "onResume - lay_login_correcto");
		super.onResume();
	}
	
	@Override
	protected void onDestroy() 
	{
		Log.d(TAG, "onDestroy - lay_login_correcto");
		super.onDestroy();
	}
	
	@Override
	protected void onPause() 
	{
		Log.d(TAG, "onPause - lay_login_correcto");
		super.onPause();
	}
	
	@Override
	protected void onStart() 
	{
		Log.d(TAG, "onStart - lay_login_correcto");
		super.onStart();
	}
	
	@Override
	protected void onRestart() 
	{
		Log.d(TAG, "onRestart - lay_login_correcto");
		super.onRestart();
	}
	
	@Override
	protected void onStop() 
	{
		Log.d(TAG, "onStop - lay_login_correcto");
		super.onStop();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		Log.d(TAG, "onSaveInstanceState - lay_login_correcto");
		super.onSaveInstanceState(outState);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) 
	{
		Log.d(TAG, "onRestoreInstanceState - lay_login_correcto");
		super.onRestoreInstanceState(savedInstanceState);
	}


}
