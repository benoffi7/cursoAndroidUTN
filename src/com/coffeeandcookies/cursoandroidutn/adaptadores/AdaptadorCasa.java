package com.coffeeandcookies.cursoandroidutn.adaptadores;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coffeeandcookies.cursoandroidutn.R;
import com.coffeeandcookies.cursoandroidutn.objetos.Casa;

public class AdaptadorCasa extends BaseAdapter 
{
	private ArrayList<Casa> casas;
	private Context context;

	public AdaptadorCasa(ArrayList<Casa> Casas, Context Context) 
	{
		this.casas = Casas;
		this.context = Context;
	}

	@Override
	public int getCount() 
	{
		return casas.size();
	}

	@Override
	public Casa getItem(int arg0) 
	{
		return casas.get(arg0);
	}

	@Override
	public long getItemId(int arg0) 
	{
		return 0;
	}
	
	static class ViewHolder 
    {
		TextView text_renglon;
		LinearLayout ll_renglon;
    }

	 @Override
	 public View getView(final int position, View convertView, ViewGroup parent) 
	 {
	        Casa item = getItem(position);
	        ViewHolder holder;
	        if (convertView == null) 
	        {
	            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            convertView = li.inflate(R.layout.renglon_casa, parent, false);
	            holder = new ViewHolder();
	            holder.text_renglon = (TextView) convertView.findViewById(R.id.text_renglon_casa);
	            holder.ll_renglon = (LinearLayout) convertView.findViewById(R.id.ll_renglon_casa);
	            convertView.setTag(holder);
	        } 
	        else 
	        {
	            holder = (ViewHolder) convertView.getTag();
	        }

	        holder.text_renglon.setText(item.getDireccion()); 

	        return convertView;
	    }
}
