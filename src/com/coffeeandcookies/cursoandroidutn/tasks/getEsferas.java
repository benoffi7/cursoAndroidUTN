package com.coffeeandcookies.cursoandroidutn.tasks;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.coffeeandcookies.cursoandroidutn.objetos.Esfera;

public class getEsferas extends AsyncTask<Void, Void, ArrayList<Esfera>>
{

	String URL = "http://gonzalobenoffi.apiary.io/esferas";
	Activity ac;
	ProgressDialog pd;

	public getEsferas(Activity ac) 
	{
		this.ac = ac;
	}
	
	@Override
	protected void onPreExecute() 
	{
		pd = new ProgressDialog(ac);
		pd.setMessage("Cargando...");
		pd.show();
		super.onPreExecute();
	}
	
	@Override
	protected ArrayList<Esfera> doInBackground(Void... params) 
	{
		ArrayList<Esfera> esferas = new ArrayList<Esfera>();
		try 
 		{
	 		StringBuilder builder = new StringBuilder();
	 		HttpClient client = new DefaultHttpClient();
	 		HttpGet httpGet = new HttpGet(URL);
	 		Log.d("Curso", URL);	 		
 			HttpResponse response = client.execute(httpGet);
 			StatusLine statusLine = response.getStatusLine();
 			int statusCode = statusLine.getStatusCode();
 			if (statusCode == 200) 
 			{	
 				HttpEntity entity = response.getEntity();
 				InputStream content = entity.getContent();
 				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
 				String line;
 				while ((line = reader.readLine()) != null) 
 				{
 						builder.append(line);
 				}
 	 			
 	 	    	if (builder.toString().length()>0)
 	 	    	{    		 	    			
 	 	    		JSONObject jsonObject = new JSONObject(builder.toString());  	    		 	
 	 	    		JSONArray esferasJSON = jsonObject.getJSONArray("esferas");
 	 	    		
 	 	    		Log.d("Curso", "Cantidad de esferas: "+esferasJSON.length());
 	 	    		for (int i=0;i<esferasJSON.length();i++)
 	 	    		{
 	 	    			JSONObject esferaJSON = esferasJSON.getJSONObject(i);
 	 	    			Esfera oEsfera = new Esfera();
 	 	    			oEsfera.setId(esferaJSON.getInt("id"));
 	 	    			oEsfera.setNombre(esferaJSON.getString("nombre"));
 	 	    			oEsfera.setDescripcion(esferaJSON.getString("descripcion"));
 	 	    			oEsfera.setLatitud(esferaJSON.getString("latitud"));
 	 	    			oEsfera.setLongitud(esferaJSON.getString("longitud"));
 	 	    			esferas.add(oEsfera);
 	 	    		}
 	 	   		}
 	 	    	else
 	 	    	{
 	 	    		Log.e("Curso", "Error");
 	 	    	}
 			}
 			else
 			{
 				Log.e("Curso", "Error SL "+statusCode);
 			}
 		}
 	    catch(Exception e)
 	    {
 	    	e.printStackTrace(); 	          
 	    	Log.e("Curso", "Error");
 	    }
		return esferas;
	}
	
	@Override
	protected void onPostExecute(ArrayList<Esfera> esferas) 
	{
		if (pd.isShowing())
		{
			pd.dismiss();
		}
		Toast.makeText(ac, "Cantidad de esferas: "+esferas.size(), Toast.LENGTH_SHORT).show();
		super.onPostExecute(esferas);
	}
}
