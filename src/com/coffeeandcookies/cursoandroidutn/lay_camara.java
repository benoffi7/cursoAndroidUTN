package com.coffeeandcookies.cursoandroidutn;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class lay_camara extends Activity
{
	protected static final int CAMERA_REQUEST = 0;
	protected static final int PICKER_GALLERY = 1;
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
				 Intent action = new Intent("android.media.action.IMAGE_CAPTURE");
                 action.putExtra(MediaStore.EXTRA_OUTPUT,MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());
                 startActivityForResult(action, CAMERA_REQUEST);

			}
		});
		btn_galeria.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
    			photoPickerIntent.setType("image/*");
    			startActivityForResult(photoPickerIntent, PICKER_GALLERY);

			}
		});
	}

	private void levantarXML()
	{
		imageView_result = (ImageView) findViewById(R.id.imageView_result);
		btn_camara = (Button) findViewById(R.id.btn_camara);
		btn_galeria = (Button) findViewById(R.id.btn_galeria);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{  
		try
		{
			if ((requestCode == CAMERA_REQUEST) && (resultCode== RESULT_OK))
	        {          	
	        	try
		        {
  		        	Bitmap photo = (Bitmap) data.getExtras().get("data");
           	        System.gc();
		        	ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
			        photo.compress(Bitmap.CompressFormat.JPEG, 70, baos); //bm is the bitmap object   
			        imageView_result.setImageBitmap(photo);
		            System.gc();
			       
			        baos.close();
			        baos=null;
		        }
	        	catch (IllegalArgumentException e)
	        	{
	        		e.printStackTrace();
	        	}
	        	catch (Error e)
		        {
	        		e.printStackTrace();
		        }
		        catch (Exception ex)
		        {
	        	   ex.printStackTrace();
		        }	            
	        }
	        if ((requestCode == PICKER_GALLERY) && (resultCode== RESULT_OK))
	        {      	
	            try
		        {
	            	Uri chosenImageUri = data.getData();
		            Bitmap photoBitMap = Media.getBitmap(this.getContentResolver(), chosenImageUri);
	       		 	System.gc();		  			            
			        ByteArrayOutputStream baos = new ByteArrayOutputStream();
			        photoBitMap.compress(Bitmap.CompressFormat.JPEG, 70, baos);	          
			        imageView_result.setImageBitmap(photoBitMap);			       
			        System.gc();	
			        baos.close();
			        baos=null;
		           
		        }
	            catch (Error e)
		        {
	        	   e.printStackTrace();
		        }
		        catch (Exception ex)
		        {
		        	ex.printStackTrace();
		        }
	        }	       
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
    }
}
