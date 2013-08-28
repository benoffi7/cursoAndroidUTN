package com.coffeeandcookies.cursoandroidutn;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ListView;

import com.coffeeandcookies.cursoandroidutn.adaptadores.AdaptadorCasa;
import com.coffeeandcookies.cursoandroidutn.objetos.Casa;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        
        ArrayList<Casa> Casas = new ArrayList<Casa>();
        
        Casa oCasa = new Casa();
        oCasa.setDireccion("Independencia 1150");
        Casas.add(oCasa);
        
        Casa oCasa1 = new Casa();
        oCasa1.setDireccion("Salta 4021");
        Casas.add(oCasa1);
        
        AdaptadorCasa adaptadorCasa = new AdaptadorCasa(Casas, getApplicationContext());
        ListView listView = (ListView)findViewById(R.id.lista);
        listView.setAdapter(adaptadorCasa);
        
        //acceso a recurso del sistema
        String nombre     = getResources().getString(R.string.app_name);
        Drawable imagen   = getResources().getDrawable(R.drawable.ic_launcher);
        String[] arreglo  = getResources().getStringArray(R.array.colores);
        int idColor       = getResources().getColor(R.color.azul);        
    }
}
