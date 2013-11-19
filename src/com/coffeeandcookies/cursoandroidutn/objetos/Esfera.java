package com.coffeeandcookies.cursoandroidutn.objetos;


public class Esfera 
{
	int id;
	String nombre;
	String descripcion;
	String latitud;
	String longitud;
	
	public Esfera() {
		// TODO Auto-generated constructor stub
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public int getId() {
		return id;
	}
	
	public String getLatitud() {
		return latitud;
	}
	
	public String getLongitud() {
		return longitud;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
