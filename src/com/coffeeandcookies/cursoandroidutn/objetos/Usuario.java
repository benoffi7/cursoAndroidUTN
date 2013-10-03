package com.coffeeandcookies.cursoandroidutn.objetos;

public class Usuario
{
	String user;
	String pass;
	String email;
	int edad;
	
	public Usuario()
	{
		// TODO Auto-generated constructor stub
	}
	
	
	
	public int getEdad() {
		return edad;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPass()
	{
		return pass;
	}
	
	public String getUser()
	{
		return user;
	}
	
	public void setPass(String pass)
	{
		this.pass = pass;
	}
	
	public void setUser(String user)
	{
		this.user = user;
	}
	
	
	
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
