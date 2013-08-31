package com.coffeeandcookies.cursoandroidutn;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.coffeeandcookies.cursoandroidutn.adaptadores.AdaptadorMonedas;
import com.coffeeandcookies.cursoandroidutn.objetos.Conversion;

public class lay_conversor extends Activity 
{
	private Spinner spinner_monedas_origen;
	private EditText edit_valor;
	private CheckBox check_01;
	private CheckBox check_02;
	private CheckBox check_03;
	private CheckBox check_04;
	private CheckBox check_05;
	private CheckBox check_06;
	private Button button_convertir;
	private ListView list_conversiones_realizadas;
	
	private String[] monedas;
	int monedaSeleccionada = 0;
	//Double[] cotizaciones = {1.00,8.00,0.73,97.991054,8.75752,5.64}; base dolar
	Double[] cotizaciones = {0.17,0.11,0.14,0.05755,0.11,1.00}; //base peso
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		setContentView(R.layout.lay_conversor);
		levantarXML();
		setupChecks();
		setupCombo();
		setupEventos();
		super.onCreate(savedInstanceState);
	}
	
	private void setupEventos() 
	{
		button_convertir.setOnClickListener(new OnClickListener()
		{			
			@Override
			public void onClick(View v) 
			{
				list_conversiones_realizadas.setAdapter(null);
				buscarChecks();
			}
		});
		spinner_monedas_origen.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int pos, long arg3) 
			{
				monedaSeleccionada = pos;				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0)
			{
				// TODO Auto-generated method stub				
			}
		});		
	}

	protected void buscarChecks() 
	{
		double valor = 1.00;
		
		if (edit_valor.getText().toString().length()>0)
		{
			valor = Double.parseDouble(edit_valor.getText().toString());
		}
		
		ArrayList<Conversion> conversiones = new ArrayList<Conversion>();
		
		if (check_01.isChecked())
		{
			Conversion oConversion = new Conversion();
			oConversion.setNombre(monedas[0]);
			oConversion.setPais(getResources().getDrawable(R.drawable.ic_flag_usa));					
	    	double cotizacion = ((1/cotizaciones[0])*cotizaciones[monedaSeleccionada]);	
			oConversion.setCotizacion(cotizacion*valor);
			conversiones.add(oConversion);
		}
		if (check_02.isChecked())
		{
			Conversion oConversion = new Conversion();
			oConversion.setNombre(monedas[1]);
			oConversion.setPais(getResources().getDrawable(R.drawable.ic_flag_usa));					
	    	double cotizacion = ((1/cotizaciones[1])*cotizaciones[monedaSeleccionada]);	
			oConversion.setCotizacion(cotizacion*valor);
			conversiones.add(oConversion);
		}
		if (check_03.isChecked())
		{
			Conversion oConversion = new Conversion();
			oConversion.setNombre(monedas[2]);
			oConversion.setPais(getResources().getDrawable(R.drawable.ic_flag_euro));					
	    	double cotizacion = ((1/cotizaciones[2])*cotizaciones[monedaSeleccionada]);	
			oConversion.setCotizacion(cotizacion*valor);
			conversiones.add(oConversion);
		}
		
		if (check_04.isChecked())
		{
			Conversion oConversion = new Conversion();
			oConversion.setNombre(monedas[3]);
			oConversion.setPais(getResources().getDrawable(R.drawable.ic_flag_japan));					
	    	double cotizacion = ((1/cotizaciones[3])*cotizaciones[monedaSeleccionada]);	
			oConversion.setCotizacion(cotizacion*valor);
			conversiones.add(oConversion);
		}
		if (check_05.isChecked())
		{
			Conversion oConversion = new Conversion();
			oConversion.setNombre(monedas[4]);
			oConversion.setPais(getResources().getDrawable(R.drawable.ic_flag_england));					
	    	double cotizacion = ((1/cotizaciones[4])*cotizaciones[monedaSeleccionada]);	
			oConversion.setCotizacion(cotizacion*valor);
			conversiones.add(oConversion);
		}
		if (check_06.isChecked())
		{
			Conversion oConversion = new Conversion();
			oConversion.setNombre(monedas[5]);
			oConversion.setPais(getResources().getDrawable(R.drawable.ic_flag_arg));					
	    	double cotizacion = ((1/cotizaciones[5])*cotizaciones[monedaSeleccionada]);	
			oConversion.setCotizacion(cotizacion*valor);
			conversiones.add(oConversion);
		}
		
		if (conversiones.size()==0)
		{
			Toast.makeText(getApplicationContext(), "No selecciono nada", Toast.LENGTH_SHORT).show();
		}
		else
		{
			Toast.makeText(getApplicationContext(), "Realizando conversión", Toast.LENGTH_SHORT).show();
			AdaptadorMonedas adapter = new AdaptadorMonedas(conversiones, getApplicationContext(),monedas[monedaSeleccionada]);
			list_conversiones_realizadas.setAdapter(adapter);
		}
	}
	
	private void setupCombo()
	{
		 ArrayAdapter<String> adaptador_monedas = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,monedas);	        
		 adaptador_monedas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		 spinner_monedas_origen.setAdapter(adaptador_monedas);
	}

	private void setupChecks() 
	{
		monedas = getResources().getStringArray(R.array.monedas);
		check_01.setText(monedas[0]);
		check_02.setText(monedas[1]);
		check_03.setText(monedas[2]);
		check_04.setText(monedas[3]);
		check_05.setText(monedas[4]);
		check_06.setText(monedas[5]);		
	}

	private void levantarXML()
	{ 
		spinner_monedas_origen = (Spinner)findViewById(R.id.spinner_monedas_origen);
		edit_valor =  (EditText)findViewById(R.id.edit_valor);
		check_01 = (CheckBox)findViewById(R.id.check_01);
		check_02 = (CheckBox)findViewById(R.id.check_02);
		check_03 = (CheckBox)findViewById(R.id.check_03);
		check_04 = (CheckBox)findViewById(R.id.check_04);
		check_05 = (CheckBox)findViewById(R.id.check_05);
		check_06 = (CheckBox)findViewById(R.id.check_06);
		button_convertir = (Button)findViewById(R.id.button_convertir);
		list_conversiones_realizadas = (ListView)findViewById(R.id.list_conversiones_realizadas);
	}
}
