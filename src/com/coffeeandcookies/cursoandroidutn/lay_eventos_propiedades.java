package com.coffeeandcookies.cursoandroidutn;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class lay_eventos_propiedades extends Activity 
{
	private SeekBar  barra;
	private TextView text;
	private Button 	 button;
	private EditText edit;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		setContentView(R.layout.lay_seekbar);
		levantarXML();
		setupBarra();	
		setupBoton();
		setupEdit();
		 //acceso a recurso del sistema
        String nombre     = getResources().getString(R.string.app_name);
        Drawable imagen   = getResources().getDrawable(R.drawable.ic_launcher);
        String[] arreglo  = getResources().getStringArray(R.array.colores);
        int idColor       = getResources().getColor(R.color.azul);      
		
		
		super.onCreate(savedInstanceState);
	}

	private void setupEdit() 
	{
		edit.setOnFocusChangeListener(new OnFocusChangeListener() 
		{			
			@Override
			public void onFocusChange(View v, boolean hasFocus) 
			{
				text.setText("onFocusChange: "+hasFocus);
			}
		});
		edit.setOnKeyListener(new View.OnKeyListener() 
		{			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) 
			{
				text.setText("onKey: keyCode:"+keyCode+" keyEvente: "+event.getAction());
				return false;
			}
		});
		edit.addTextChangedListener(new TextWatcher() 
		{
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) 
			{
				text.setText("onTextChanged: CharSequence:"+s);				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,	int after) 
			{
				// TODO Auto-generated method stub				
			}
			
			@Override
			public void afterTextChanged(Editable s) 
			{
				// TODO Auto-generated method stub				
			}
		});
	}

	private void setupBoton() 
	{
		button.setOnClickListener(new View.OnClickListener() 
		{			
			@Override
			public void onClick(View v) 
			{
				text.setText("Click en el boton");				
			}
		});
		button.setOnLongClickListener(new View.OnLongClickListener() 
		{			
			@Override
			public boolean onLongClick(View v) 
			{
				text.setText("Long-Click en el boton");
				return false;
			}
		});
	}

	private void setupBarra() 
	{
		barra.setProgress(50);
		text.setText("Valor: " + barra.getProgress());
		barra.setMax(100);
		barra.setOnSeekBarChangeListener(new OnSeekBarChangeListener() 
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
				text.setText("Valor: " + progress);
			}
		});		
	}



	private void levantarXML()
	{
		barra = (SeekBar) findViewById(R.id.barra);
		text = (TextView) findViewById(R.id.text);
	}
}
