package com.coffeeandcookies.cursoandroidutn;

import java.util.ArrayList;

import com.coffeeandcookies.cursoandroidutn.objetos.Casa;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class lay_eventos_propiedades extends Activity 
{
	private SeekBar barra_percha;
	private TextView text_percha;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		setContentView(R.layout.lay_seekbar);
		levantarXML();
		setupBarra();
	
		
		super.onCreate(savedInstanceState);
	}

	private void setupBarra() 
	{
		barra_percha.setProgress(50);
		text_percha.setText("Valor: " + barra_percha.getProgress());
		barra_percha.setMax(100);
		barra_percha.setOnSeekBarChangeListener(new OnSeekBarChangeListener() 
		{

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) 
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) 
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) 
			{
				text_percha.setText("Valor: " + progress);
			}
		});
		
	}

	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() 
	{
		// TODO Auto-generated method stub
		super.onPause();
	}

	private void levantarXML()
	{
		barra_percha = (SeekBar) findViewById(R.id.barra_percha);
		text_percha = (TextView) findViewById(R.id.text_percha);
	}
}
