package com.example.App.model;

public class Fichaje {
	
	private int id;
	private int idUsuario;
	private String fechaEntrada;
	private String fechaSalida;
	private int solicitado;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(String fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	public String getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public int getSolicitado() {
		return solicitado;
	}
	public void setSolicitado(int solicitado) {
		this.solicitado = solicitado;
	}
	
}
