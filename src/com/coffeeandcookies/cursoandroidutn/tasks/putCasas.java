package com.coffeeandcookies.cursoandroidutn.tasks;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class putCasas extends AsyncTask<Void, Void, Boolean>
{
	Activity ac;
	ProgressDialog pd;
	String URL = "http://gonzalobenoffi.apiary.io/casas";
	String ubicacion;
	int cantidadAmbiente;
	
	public putCasas(Activity ac, String ubicacion, int cantidadAmbientes) 
	{
		this.ac = ac;
		this.ubicacion = ubicacion;
		this.cantidadAmbiente = cantidadAmbientes;
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
	protected Boolean doInBackground(Void... params) 
	{
		Boolean resultado = false;
		try 
 		{
	 		StringBuilder builder = new StringBuilder();
	 		HttpClient client = new DefaultHttpClient();
	 		HttpPost httpPost = new HttpPost(URL);
	 			 		
	 		JSONObject objectRoot = new JSONObject();
			objectRoot .put("ubicacion", ubicacion);
	 		objectRoot.put("cantidadAmbientes", cantidadAmbiente);	 		
	 		
	 		String message = objectRoot.toString();
	 		httpPost.setEntity(new StringEntity(message, "UTF8"));	 	
	 		 	
	 		Log.d("Curso", URL+": "+message);	 		
 			HttpResponse response = client.execute(httpPost);
 			StatusLine statusLine = response.getStatusLine();
 			int statusCode = statusLine.getStatusCode();
 			if (statusCode == 201) 
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
 	 	    		String status = jsonObject.getString("status");
 	 	    		String errores = jsonObject.getString("errores");
 	 	    		Log.d("Curso", "Respuesta: "+status+" "+errores);
 	 	    		resultado = true;
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
		return resultado;
	}
	
	@Override
	protected void onPostExecute(Boolean resultado) 
	{
		if (pd.isShowing())
		{
			pd.dismiss();
		}
		Toast.makeText(ac, "resultado: "+resultado, Toast.LENGTH_SHORT).show();
		super.onPostExecute(resultado);
	}

}
