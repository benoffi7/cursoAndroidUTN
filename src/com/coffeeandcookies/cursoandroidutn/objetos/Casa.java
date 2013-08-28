package com.coffeeandcookies.cursoandroidutn.objetos;

public class Casa 
{
	String direccion;
	int cantidadAmbientes;
	
	public Casa() {
		// TODO Auto-generated constructor stub
	}
	
	public void setCantidadAmbientes(int cantidadAmbientes) {
		this.cantidadAmbientes = cantidadAmbientes;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public int getCantidadAmbientes() {
		return cantidadAmbientes;
	}
	
	public String getDireccion() {
		return direccion;
	}

}
