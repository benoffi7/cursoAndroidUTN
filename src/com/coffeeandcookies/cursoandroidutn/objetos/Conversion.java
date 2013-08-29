package com.coffeeandcookies.cursoandroidutn.objetos;

import android.graphics.drawable.Drawable;

public class Conversion 
{
	Drawable pais;
	String nombre;
	double cotizacion;
	
	public Conversion() {
		// TODO Auto-generated constructor stub
	}
	
	public double getCotizacion() {
		return cotizacion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Drawable getPais() {
		return pais;
	}
	
	public void setCotizacion(double cotizacion) {
		this.cotizacion = cotizacion;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setPais(Drawable pais) {
		this.pais = pais;
	}

}
