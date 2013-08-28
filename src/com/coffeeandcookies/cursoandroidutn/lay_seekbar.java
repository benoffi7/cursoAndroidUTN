package com.coffeeandcookies.cursoandroidutn;

import java.util.ArrayList;

import com.coffeeandcookies.cursoandroidutn.objetos.Casa;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class lay_seekbar extends Activity 
{
	private SeekBar barra_percha;
	private TextView text_percha;
	private ArrayList<Casa> Casas;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		setContentView(R.layout.lay_seekbar);
		levantarXML();

		Casas = new ArrayList<Casa>();
		
		Casa oCasa = new Casa();
		oCasa.setDireccion("Independencia 1150");
		Casas.add(oCasa);

		Casa oCasa1 = new Casa();
		oCasa1.setDireccion("Salta 4021");
		Casas.add(oCasa1);
		
		barra_percha.setProgress(50);
		text_percha.setText("Valor: " + barra_percha.getProgress());
		barra_percha.setMax(100);
		barra_percha.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

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
				if (progress <= 1)
				{
					text_percha.setText((text_percha.getText().toString() + " " + Casas.get(progress).getDireccion()));
				}
			}
		});
		super.onCreate(savedInstanceState);
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
