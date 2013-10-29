package com.coffeeandcookies.cursoandroidutn;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;

import com.coffeeandcookies.cursoandroidutn.adaptadores.AdaptadorPaginas;
import com.coffeeandcookies.cursoandroidutn.adaptadores.AutoCompleteAdapter;

public class lay_autocomplete extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		setContentView(R.layout.lay_autocomplete_pager);
		AutoCompleteTextView autoComplete_edit = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView_casas);
		AutoCompleteAdapter adapter = new AutoCompleteAdapter(getApplicationContext());
		autoComplete_edit.setAdapter(adapter);
		autoComplete_edit.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) 
			{
				Log.d("Autocomplete", "Seleccione algo: "+arg1.getTag().toString());				
			}
		});
		
		ViewPager paginador = (ViewPager)findViewById(R.id.imagepager);
		AdaptadorPaginas adap_pag = new AdaptadorPaginas();
		paginador.setAdapter(adap_pag);
		paginador.setCurrentItem(0);
		
		super.onCreate(savedInstanceState);
	}
}
