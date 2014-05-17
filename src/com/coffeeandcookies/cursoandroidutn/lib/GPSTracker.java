package com.coffeeandcookies.cursoandroidutn.lib;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class GPSTracker extends Service
{

	// flag for GPS status
	boolean isGPSEnabled = false;

	// flag for network status
	boolean isNetworkEnabled = false;

	// flag for GPS status
	boolean canGetLocation = false;

	Location location; // location
	double latitude; // latitude
	double longitude; // longitude

	// Declaring a Location Manager
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

	public class GetLocations extends AsyncTask<Void, Void, Void>
	{

		@Override
		protected void onPreExecute()
		{
			// TODO Auto-generated method stub
			super.onPreExecute();
			locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			// getting GPS status
			isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			// getting network status
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