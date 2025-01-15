package com.example.App.model;

public class Usuario {
	
	private int id;
	private String nombre;
	private String password;
	private int esAdmin;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getEsAdmin() {
		return esAdmin;
	}
	public void setEsAdmin(int esAdmin) {
		this.esAdmin = esAdmin;
	}
}
