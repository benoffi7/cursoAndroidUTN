package com.coffeeandcookies.cursoandroidutn;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.coffeeandcookies.cursoandroidutn.adaptadores.AdaptadorCasa;
import com.coffeeandcookies.cursoandroidutn.objetos.Casa;

public class lay_listas extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        
        //COMBOS
        
        //Datos: pueden ser una array de String o un array cargado en los recursos.
        
        final String[] 	elementos = new String[]{"Elem1","Elem2","Elem3","Elem4","Elem5"};
        final String[]  colores = getResources().getStringArray(R.array.colores);
        
        //Instanciamos el Adaptador
        
        ArrayAdapter<String> adaptador_elementos = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,elementos);
        ArrayAdapter<String> adaptador_colores =  new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, colores);
        
       //Configuracion adicional para los spinner - Se define el tipo de lista
        
        adaptador_elementos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adaptador_colores.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        //Obtenemos la referencia del componente XML en Java

        Spinner comboElementos = (Spinner)findViewById(R.id.spinner_elementos);
        Spinner comboColores = (Spinner)findViewById(R.id.spinner_colores);

        comboElementos.setAdapter(adaptador_elementos);
        comboColores.setAdapter(adaptador_colores);
        
        comboColores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() 
        {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int pos, long arg3)
			{
				Toast.makeText(getApplicationContext(), "Color seleccionado: "+colores[pos], Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0)
			{
				// TODO Auto-generated method stub
				
			}
		});

        comboElementos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int pos, long arg3)
			{
				Toast.makeText(getApplicationContext(), "Elemento seleccionado: "+elementos[pos], Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) 
			{
				// TODO Auto-generated method stub
				
			}
		});
        
        //LISTA
        
        ArrayList<Casa> Casas = new ArrayList<Casa>();
        
        Casa oCasa = new Casa();
        oCasa.setDireccion("Independencia 1150");
        Casas.add(oCasa);
        
        Casa oCasa1 = new Casa();
        oCasa1.setDireccion("Salta 4021");
        Casas.add(oCasa1);    
        
        AdaptadorCasa adaptadorCasa = new AdaptadorCasa(Casas, getApplicationContext());
        ListView listView = (ListView)findViewById(R.id.list_casas);
        listView.setAdapter(adaptadorCasa);  
    }
}
