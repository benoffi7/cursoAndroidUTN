package com.coffeeandcookies.cursoandroidutn.adaptadores;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.coffeeandcookies.cursoandroidutn.R;

public class AdaptadorPaginas extends PagerAdapter 
{	
	public AdaptadorPaginas()
	{
		
	}
    public int getCount() 
    {
        return 5;
    }
    
    public Object instantiateItem(View collection, int position) 
    {
        LayoutInflater inflater = (LayoutInflater) collection.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);        
        View view = inflater.inflate(R.layout.imagen, null);
        ImageView imageView = (ImageView)view.findViewById(R.id.image_full);
		
          
        ((ViewPager) collection).addView(view, 0);
        return view;
    }
    
    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) 
    {
        ((ViewPager) arg0).removeView((View) arg2);
    }
    
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) 
    {
        return arg0 == ((View) arg1);
    }
    
    @Override
    public Parcelable saveState() 
    {
        return null;
    }
}