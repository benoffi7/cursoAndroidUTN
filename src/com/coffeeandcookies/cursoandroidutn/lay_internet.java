package com.coffeeandcookies.cursoandroidutn;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.coffeeandcookies.cursoandroidutn.tasks.getEsferas;
import com.coffeeandcookies.cursoandroidutn.tasks.putCasas;

public class lay_internet extends Activity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		setContentView(R.layout.lay_internet);
		findViewById(R.id.button_get).setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				new getEsferas(lay_internet.this).execute();
			}
		});
		findViewById(R.id.button_post).setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				new putCasas(lay_internet.this, "casas", 2).execute();
			}
		});
		super.onCreate(savedInstanceState);
	}
}
