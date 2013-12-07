package https;

import java.net.URI;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.coffeeandcookies.cursoandroidutn.objetos.Esfera;

public class getHttps extends AsyncTask<Void, Void, Void>
{
	Activity ac;
	ProgressDialog pd;

	public getHttps(Activity ac) 
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
	protected Void doInBackground(Void... params) 
	{
		try 
 		{
			HttpGet post = new HttpGet(new URI("https://google.com"));
			 
			HttpClient  client = new DefaultHttpClient();
			client=sslClient(client);
			 
			// Execute HTTP Post Request
			 
			HttpResponse result = client.execute(post);
			Log.v("Curso", EntityUtils.toString(result.getEntity()));
 		}
 	    catch(Exception e)
 	    {
 	    	e.printStackTrace(); 	          
 	    	Log.e("Curso", "Error");
 	    }
		return null;
	}
	
	private HttpClient sslClient(HttpClient client)
	{
		try
		{
			X509TrustManager tm = new X509TrustManager()
			{
				public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException
				{
				}

				public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException
				{
				}

				public X509Certificate[] getAcceptedIssuers()
				{
					return null;
				}
			};
			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(null, new TrustManager[]
			{ tm }, null);
			SSLSocketFactory ssf = new MySSLSocketFactory(ctx);
			ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			ClientConnectionManager ccm = client.getConnectionManager();
			SchemeRegistry sr = ccm.getSchemeRegistry();
			sr.register(new Scheme("https", ssf, 443));
			return new DefaultHttpClient(ccm, client.getParams());
		}
		catch (Exception ex)
		{
			return null;
		}
	}
	
	@Override
	protected void onPostExecute(Void result) 
	{
		if (pd.isShowing())
		{
			pd.dismiss();
		}
		super.onPostExecute(result);
	}
}
