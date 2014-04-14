package com.coffeeandcookies.cursoandroidutn;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.Person.Image;

public class lay_google extends Activity
{
	/* Request code used to invoke sign in user interactions. */
	private static final int RC_SIGN_IN = 0;

	/* Client used to interact with Google APIs. */
	private GoogleApiClient mGoogleApiClient;

	private boolean mSignInClicked;

	private ConnectionResult mConnectionResult;

	/*
	 * A flag indicating that a PendingIntent is in progress and prevents us
	 * from starting further intents.
	 */
	private boolean mIntentInProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_google);
		findViewById(R.id.sign_in_button).setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (!mGoogleApiClient.isConnecting())
				{
					mSignInClicked = true;
					resolveSignInError();
				}
			}
		});

		mGoogleApiClient = new GoogleApiClient.Builder(this)
		
		.addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks()
		{

			@Override
			public void onConnected(Bundle connectionHint)
			{
				mSignInClicked = false;
				Toast.makeText(lay_google.this, "User is connected!", Toast.LENGTH_LONG).show();
				if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null)
				{
					Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
					String personName = currentPerson.getDisplayName();
					Image personPhoto = currentPerson.getImage();
					String personGooglePlusProfile = currentPerson.getUrl();
					String email = Plus.AccountApi.getAccountName(mGoogleApiClient);
					Log.d("nulos",personName+personPhoto.getUrl()+personGooglePlusProfile+email+currentPerson.getId());
				}
			}

			@Override
			public void onConnectionSuspended(int cause)
			{
				mGoogleApiClient.connect();

			}
		}).addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener()
		{

			@Override
			public void onConnectionFailed(ConnectionResult result)
			{
				if (!mIntentInProgress && result.hasResolution())
				{
					try
					{
						mIntentInProgress = true;
						startIntentSenderForResult(result.getResolution().getIntentSender(), RC_SIGN_IN, null, 0, 0, 0);
					}
					catch (SendIntentException e)
					{
						// The intent was canceled before it was sent. Return to
						// the default
						// state and attempt to connect to get an updated
						// ConnectionResult.
						mIntentInProgress = false;
						mGoogleApiClient.connect();
					}
				}
				if (!mIntentInProgress)
				{
					// Store the ConnectionResult so that we can use it later
					// when the user clicks
					// 'sign-in'.
					mConnectionResult = result;

					if (mSignInClicked)
					{
						// The user has already clicked 'sign-in' so we attempt
						// to resolve all
						// errors until the user is signed in, or they cancel.
						resolveSignInError();
					}
				}

			}
		}).addApi(Plus.API, null).addScope(Plus.SCOPE_PLUS_LOGIN).build();
	}

	protected void onActivityResult(int requestCode, int responseCode, Intent intent)
	{
		if (requestCode == RC_SIGN_IN)
		{
			if (responseCode != RESULT_OK)
			{
				mSignInClicked = false;
			}

			mIntentInProgress = false;

			if (!mGoogleApiClient.isConnecting())
			{
				mGoogleApiClient.connect();
			}
		}
	}

	protected void onStart()
	{
		super.onStart();
		mGoogleApiClient.connect();
	}

	private void resolveSignInError()
	{
		if (mConnectionResult.hasResolution())
		{
			try
			{
				mIntentInProgress = true;
				startIntentSenderForResult(mConnectionResult.getResolution().getIntentSender(), RC_SIGN_IN, null, 0, 0, 0);
			}
			catch (SendIntentException e)
			{
				// The intent was canceled before it was sent. Return to the
				// default
				// state and attempt to connect to get an updated
				// ConnectionResult.
				mIntentInProgress = false;
				mGoogleApiClient.connect();
			}
		}
	}

	protected void onStop()
	{
		super.onStop();

		if (mGoogleApiClient.isConnected())
		{
			mGoogleApiClient.disconnect();
		}
	}
}
