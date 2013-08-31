package com.coffeeandcookies.cursoandroidutn.adaptadores;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.coffeeandcookies.cursoandroidutn.R;
import com.coffeeandcookies.cursoandroidutn.objetos.Conversion;

public class AdaptadorMonedas extends BaseAdapter 
{
	private ArrayList<Conversion> conversiones;
	private Context context;
	private String moneda;
	static DecimalFormat df = new DecimalFormat("####.##");

	public AdaptadorMonedas(ArrayList<Conversion> Conversiones, Context Context, String moneda) 
	{
		this.conversiones = Conversiones;
		this.context = Context;
		this.moneda = moneda;
	}

	@Override
	public int getCount() 
	{
		return conversiones.size();
	}

	@Override
	public Conversion getItem(int arg0) 
	{
		return conversiones.get(arg0);
	}

	@Override
	public long getItemId(int arg0) 
	{
		return 0;
	}
	
	static class ViewHolder 
    {
		TextView  text_renglon;
		ImageView image_rengon;
    }

	 @Override
	 public View getView(final int position, View convertView, ViewGroup parent) 
	 {
		 Conversion item = getItem(position);
	        ViewHolder holder;
	        if (convertView == null) 
	        {
	            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            convertView = li.inflate(R.layout.renglon_conversion, parent, false);
	            holder = new ViewHolder();
	            holder.text_renglon = (TextView) convertView.findViewById(R.id.text_renglon);
	            holder.image_rengon = (ImageView)convertView.findViewById(R.id.image_renglon);
	            
	            convertView.setTag(holder);
	        } 
	        else 
	        {
	            holder = (ViewHolder) convertView.getTag();
	        }

	        holder.text_renglon.setText(item.getNombre()+": $"+df.format(item.getCotizacion())+" "+moneda); 
	        holder.image_rengon.setImageDrawable(item.getPais());
	        return convertView;
	    }
}