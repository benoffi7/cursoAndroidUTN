package com.coffeeandcookies.cursoandroidutn;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.FacebookRequestError;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.RequestAsyncTask;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;

public class lay_facebook extends Activity
{
	private Button shareButton;
	private static final List<String> PERMISSIONS = Arrays.asList("publish_actions");
	private boolean pendingPublishReauthorization = false;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		setContentView(R.layout.lay_facebook);
		shareButton = (Button) findViewById(R.id.shareButton);
		shareButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				publishStory();
			}
		});
		generarClave();
		/**
		 * If allowLoginUI is true, this will create a new Session, make it
		 * active, and open it. If the default token cache is not available,
		 * then this will request basic permissions. If the default token cache
		 * is available and cached tokens are loaded, this will use the cached
		 * token and associated permissions. If allowedLoginUI is false, this
		 * will only create the active session and open it if it requires no
		 * user interaction (i.e. the token cache is available and there are
		 * cached tokens).
		 */
		Session.openActiveSession(this, true, new Session.StatusCallback()
		{
			// callback when session changes state
			@Override
			public void call(Session session, SessionState state, Exception exception)
			{
				if (session.isOpened())
				{
					// make request to the /me API
					Request.executeMeRequestAsync(session, new Request.GraphUserCallback()
					{
						// callback after Graph API response with user object
						@Override
						public void onCompleted(GraphUser user, Response response)
						{
							if (user != null)
							{
								Log.d("KeyHash:", user.getName());
								shareButton.setVisibility(View.VISIBLE);
							}
						}
					});
				}
			}
		});

		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
		Log.d("KeyHash:", "" + requestCode + " " + resultCode);
		if (pendingPublishReauthorization)
		{
			Session session = Session.getActiveSession();
			if (session != null)
			{
				List<String> permissions = session.getPermissions();
				if (!isSubsetOf(PERMISSIONS, permissions))
				{
					// mostrar mensaje
				}
				else
				{
					publishStory();
				}
			}
		}
	}

	private void generarClave()
	{
		try
		{
			PackageInfo info = getPackageManager().getPackageInfo("com.coffeeandcookies.cursoandroidutn", PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures)
			{
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
			}
		}
		catch (NameNotFoundException e)
		{

		}
		catch (NoSuchAlgorithmException e)
		{

		}
	}

	// posting

	private boolean isSubsetOf(Collection<String> subset, Collection<String> superset)
	{
		for (String string : subset)
		{
			if (!superset.contains(string))
			{
				return false;
			}
		}
		return true;
	}

	private void publishStory()
	{
		Session session = Session.getActiveSession();
		pendingPublishReauthorization = false;
		if (session != null)
		{

			// Check for publish permissions
			List<String> permissions = session.getPermissions();
			if (!isSubsetOf(PERMISSIONS, permissions))
			{
				pendingPublishReauthorization = true;
				Session.NewPermissionsRequest newPermissionsRequest = new Session.NewPermissionsRequest(this, PERMISSIONS);
				session.requestNewPublishPermissions(newPermissionsRequest);
				return;
			}

			Bundle postParams = new Bundle();
			postParams.putString("name", "Facebook SDK for Android");
			postParams.putString("caption", "Build great social apps and get more installs.");
			postParams.putString("description", "The Facebook SDK for Android makes it easier and faster to develop Facebook integrated Android apps.");
			postParams.putString("link", "https://developers.facebook.com/android");
			postParams.putString("picture", "https://raw.github.com/fbsamples/ios-3.x-howtos/master/Images/iossdk_logo.png");

			Request.Callback callback = new Request.Callback()
			{
				public void onCompleted(Response response)
				{
					JSONObject graphResponse = response.getGraphObject().getInnerJSONObject();
					String postId = null;
					try
					{
						postId = graphResponse.getString("id");
					}
					catch (JSONException e)
					{
						Log.i("KeyHash:", "JSON error " + e.getMessage());
					}
					FacebookRequestError error = response.getError();
					if (error != null)
					{
						Toast.makeText(getApplicationContext(), postId, Toast.LENGTH_SHORT).show();
					}
					else
					{
						Toast.makeText(getApplicationContext(), error.getErrorMessage(), Toast.LENGTH_LONG).show();
					}
				}
			};

			Request request = new Request(session, "me/feed", postParams, HttpMethod.POST, callback);
			RequestAsyncTask task = new RequestAsyncTask(request);
			task.execute();
		}

	}
}
