package com.coffeeandcookies.cursoandroidutn;

import java.io.File;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class LayMapa extends FragmentActivity 
{
	private GoogleMap mapa;
	private LocationManager locationManager;
	private LocationListener listenerGPS;

	@Override
	protected void onCreate(Bundle arg0) 
	{
		super.onCreate(arg0);
		setContentView(R.layout.lay_mapa);
		setupGPS();
		setupMapa();
		mostrarMarcador(1,"Esfera de Dragon I", "Plaza Peralta Ramos", Double.parseDouble("-37.999111"), Double.parseDouble("-57.562029"));
		mostrarMarcador(2,"Esfera de Dragon II", "Plaza Mitre", Double.parseDouble("-38.003541"), Double.parseDouble("-57.552159"));
		mostrarMarcador(3,"Esfera de Dragon III", "Plaza Rocha", Double.parseDouble("-37.992989"), Double.parseDouble("-57.556665"));
		mostrarMarcador(4,"Esfera de Dragon IV", "Plaza San Martín", Double.parseDouble("-37.997826"), Double.parseDouble("-57.547052"));
		mostrarMarcador(5,"Esfera de Dragon X", "Primavesi", Double.parseDouble("-38.028594"), Double.parseDouble("-57.541602"));
		mostrarMarcador(6,"Esfera de Dragon XI", "Base Naval", Double.parseDouble("-38.043365"), Double.parseDouble("-57.542589"));
		mostrarMarcador(7,"Esfera de Dragon XII", "UTN", Double.parseDouble("-38.049753"), Double.parseDouble("-57.543619"));
	
	}

	private void setupMapa()
	{
		mapa = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		mapa.setMyLocationEnabled(true);

		mapa.setOnInfoWindowClickListener(new OnInfoWindowClickListener() 
		{
			@Override
			public void onInfoWindowClick(Marker marker)
			{
				Toast.makeText(getApplicationContext(), "Presione un popup dentro del marker", Toast.LENGTH_SHORT).show();
			}
		});

		mapa.setOnMarkerClickListener(new OnMarkerClickListener()
		{
			public boolean onMarkerClick(Marker marker) 
			{
				marker.showInfoWindow();
				Toast.makeText(getApplicationContext(), "Presione un marker", Toast.LENGTH_SHORT).show();
				return true;
			}
		});		
	}
	
	private void setupGPS()
	{
		String serviceString = Context.LOCATION_SERVICE;
		locationManager = (LocationManager) getSystemService(serviceString);
		listenerGPS = new LocationListener();
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 100, listenerGPS);
		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) 
		{
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 100, listenerGPS);
		}
	}
	
	private void mostrarMarcador(int id, String nombre, String direccion, double lat, double lng)
	{	
		// buscar una imagen en la SDCard y asiganarla en el marker
		String file_path = Environment.getExternalStorageDirectory()+File.separator+"/esferas";
		File dir = new File(file_path);  	 
		if(!dir.exists())
			  				dir.mkdirs();
		File file = new File(dir, "esfera"+id+".png");
		Bitmap myBitmap =null ;
		if ( (file.exists() ) && (file.length()>0) )
		{
		  myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
		}
		
		if (myBitmap!=null)
		{
			mapa.addMarker(new MarkerOptions()
							    .position(new LatLng(lat, lng))
						        .title(nombre)							        
						        .icon(BitmapDescriptorFactory.fromBitmap(myBitmap))
						        .snippet(direccion)); 
			 myBitmap.recycle();
			 myBitmap = null;
			 System.gc();
		 }
		 else
		 {
			 //busco el id basado en el nombre
			 int drawableID = getResources().getIdentifier("esfera_"+id, "drawable", getPackageName());
			 mapa.addMarker(new MarkerOptions()
							    .position(new LatLng(lat, lng))
						        .title(nombre)	//el titulo del popup				
						        .icon(BitmapDescriptorFactory.fromResource(drawableID))
						        .snippet(direccion)); //el sub-titulo del popup
		 }
	}

	public class LocationListener implements android.location.LocationListener,	Listener
	{
		//cuando cambia la ubicación
		public void onLocationChanged(Location location) 
		{
			//recuperamos la posición
			LatLng ubicacion = new LatLng(location.getLatitude(),location.getLongitude());
			//creamos un objeto cámara y establemos zoom y posicion
			CameraPosition camPos = new CameraPosition.Builder().target(ubicacion)
					.zoom(14) // Establecemos el zoom en 14
					.build();
			CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);
			//animamos el mapa basado en esa configuración de camara
			mapa.animateCamera(camUpd3);
		}

		public void onProviderDisabled(String provider) 
		{
		}

		public void onProviderEnabled(String provider) 
		{
		}

		public void onStatusChanged(String provider, int status, Bundle extras)
		{
		}

		public void onGpsStatusChanged(int event)
		{
		}
	}
}