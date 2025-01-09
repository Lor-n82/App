package com.example.App.model;

public class Modelo {
	
	/**
	 * 
	 * @param valor
	 * @return
	 */
	public String redireccionUrl(String valor) {
		
		String url = "";
		
		if(valor.equalsIgnoreCase(valor)) {
			url = "";
		}else if(valor.equalsIgnoreCase(valor)) {
			url = "";
		}else if(valor.equalsIgnoreCase("volver")) {
			url = "/";
		}
		
		return url;
		
	}

}
