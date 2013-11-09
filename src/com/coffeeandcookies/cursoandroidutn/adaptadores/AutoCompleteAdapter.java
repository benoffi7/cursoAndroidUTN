package com.coffeeandcookies.cursoandroidutn.adaptadores;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.coffeeandcookies.cursoandroidutn.R;
import com.coffeeandcookies.cursoandroidutn.objetos.Casa;

public class AutoCompleteAdapter extends ArrayAdapter<Casa> implements Filterable 
{ 
	private LayoutInflater mInflater;
	
	public AutoCompleteAdapter(final Context context) 
	{
		super(context,-1);		
		mInflater = LayoutInflater.from(context);
	}
 
	@Override
	public View getView(final int position, final View convertView, final ViewGroup parent) 
	{
		final TextView tv;
		if (convertView != null) 
		{
			tv = (TextView) convertView;
		} 
		else 
		{
			tv = (TextView) mInflater.inflate(R.layout.autocomplete, parent, false);
		} 
		tv.setText(getItem(position).getDireccion());
		tv.setTag(getItem(position).getCantidadAmbientes());
		return tv;
	}
	
	@Override
	public Filter getFilter() 
	{
		Filter myFilter = new Filter() 
		{
			@Override
			protected FilterResults performFiltering(final CharSequence constraint) 
			{			
				ArrayList <Casa> addressList = null;
				if ((constraint != null) && (constraint.length()>3))
				{
					addressList = devolverCasas();
				}
				
				if (addressList == null)
				{
					addressList = new ArrayList<Casa>();
				}
				 
				final FilterResults filterResults = new FilterResults();
				filterResults.values = addressList;
				filterResults.count = addressList.size();
 
				return filterResults;
			}
 
			@SuppressWarnings("unchecked")
			@Override
			protected void publishResults(final CharSequence contraint, final FilterResults results) 
			{
				clear();	
				
				for (Casa address : (ArrayList<Casa>) results.values) 
				{					
					add(address);						
				} 
				
				if (results.count > 0) 
				{					
					notifyDataSetChanged();
				} 
				else
				{
					notifyDataSetInvalidated();
				}
			}
 
			@Override
			public CharSequence convertResultToString(final Object resultValue) 
			{
				return resultValue == null ? "" : ((Casa) resultValue).getDireccion();
			}
		};
		return myFilter;
	}

	protected ArrayList<Casa> devolverCasas() 
	{		
		ArrayList<Casa> casas = new ArrayList<Casa>();
		
		Casa oCasa = new Casa();
		oCasa.setCantidadAmbientes(30);
		oCasa.setDireccion("Falucho");
		casas.add(oCasa);
		
		Casa oCasa1 = new Casa();
		oCasa1.setCantidadAmbientes(60);
		oCasa1.setDireccion("Salta");
		casas.add(oCasa1);
		
		return casas;
	}
}