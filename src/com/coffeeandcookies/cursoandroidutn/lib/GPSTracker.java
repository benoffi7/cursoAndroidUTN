package com.coffeeandcookies.cursoandroidutn.lib;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class GPSTracker extends Service
{
	boolean isGPSEnabled = false;
	boolean isNetworkEnabled = false;
	Location location; 
	double latitude; 
	double longitude;

	protected LocationManager locationManager;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		super.onStartCommand(intent, flags, startId);
		return START_STICKY;
	}

	public GPSTracker()
	{
	}

	@Override
	public void onCreate()
	{
		new GetLocations().execute();
		super.onCreate();
	}
	
	@Override
	public ComponentName startService(Intent service)
	{
		// TODO Auto-generated method stub
		return super.startService(service);
	}

	public class GetLocations extends AsyncTask<Void, Void, Void>
	{

		@Override
		protected void onPreExecute()
		{
			super.onPreExecute();
			locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
			if (isNetworkEnabled)
			{
				locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, new LocationListener()
				{
					
					@Override
					public void onStatusChanged(String provider, int status, Bundle extras)
					{
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onProviderEnabled(String provider)
					{
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onProviderDisabled(String provider)
					{
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onLocationChanged(Location location)
					{
						Log.d("GEO", location.getLongitude() + " " + location.getLatitude());						
					}
				});

			}
		}

		@Override
		protected Void doInBackground(Void... params)
		{
			
			return null;
		}
	}

	
	@Override
	public IBinder onBind(Intent arg0)
	{
		return null;
	}

}