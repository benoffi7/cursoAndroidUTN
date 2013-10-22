package com.coffeeandcookies.cursoandroidutn;

import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class lay_camara extends Activity
{
	protected static final int REQUEST_CODE = 0;
	private ImageView imageView_result;
	private Button btn_camara;
	private Button btn_galeria;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		setContentView(R.layout.lay_camara);
		levantarXML();
		asignarEventos();
		super.onCreate(savedInstanceState);
	}

	private void asignarEventos()
	{
		btn_camara.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				intent.addCategory(Intent.CATEGORY_OPENABLE);
				startActivityForResult(intent, REQUEST_CODE);

			}
		});
		btn_galeria.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub

			}
		});
	}

	private void levantarXML()
	{
		imageView_result = (ImageView) findViewById(R.id.imageView_result);
		btn_camara = (Button) findViewById(R.id.btn_camara);
		btn_galeria = (Button) findViewById(R.id.btn_galeria);
	}

	@SuppressWarnings("unused")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		InputStream stream = null;
		Bitmap bitmap = null;
	    if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK)
	    {
		    try 
		    {
		        // recyle unused bitmaps
		        if (bitmap != null) 
		        {
		          bitmap.recycle();
		        }
		        stream = getContentResolver().openInputStream(data.getData());
		        bitmap = BitmapFactory.decodeStream(stream);	
		        imageView_result.setImageBitmap(bitmap);
		    } 
		    catch (Exception e) 
		    {
		        e.printStackTrace();
		        if (stream != null)
		        {
			        try 
			        {
			            stream.close();
			        } 
			        catch (Exception ea) 
			        {
			            ea.printStackTrace();
			        }
		        }
		    }
	    } 
		super.onActivityResult(requestCode, resultCode, data);
	}
}
