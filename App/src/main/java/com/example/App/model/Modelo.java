package com.example.App.model;

import java.util.HashMap;

import com.example.App.bbdd.Consultas;
import com.example.App.utils.DebugUtils;

public class Modelo {
	
	static Consultas consulta;
	static DebugUtils debug;
	
	public static HashMap<String, String> logicaLoginRegistro(String usuario, String password, String login) {
		debug = new DebugUtils();
		debug.info("Entramos al metodo logicaLoginRegistro con parametros usuario=" + usuario + " password=" + password + " login=" + login);
		consulta = new Consultas();
		debug = new DebugUtils();
		Usuario usu = null;
		HashMap<String, String> listaDatos = new HashMap<String, String>();
		String vista = null;
		String mensaje = null;
		if(login!=null && !login.equalsIgnoreCase("")) {
			debug.info(usuario + " : " + password + " Logando");
			if(usuario!=null && !usuario.equalsIgnoreCase("")) {
				usu = consulta.consultaUsuario(usuario, password);
				if(usu!=null) {
					vista = "principal";
				}else {
					vista = "index";
					mensaje = "Usuario o contraseña invalidos";
				}
			}
		}else {
			debug.info(usuario + "" + password + " Se registra");
			if(usuario!=null && !usuario.equalsIgnoreCase("")) {
				usu = consulta.consultaUsuario(usuario, password);
				if(usu==null) {
					consulta.registrarUsuario(usuario, password);
				}else {
					debug.info("El usuario " + usuario + " ya esta registrado");
					vista = "index";
					mensaje = "El usuario ya esta registrado";
				}
				
			}
		}
		
		listaDatos.put("vista", vista);
		listaDatos.put("mensaje", mensaje);
		if(usu!=null){
			listaDatos.put("idUsuario", String.valueOf(usu.getId()));
		}
		debug.info("Salimos de logicaLoginRegistro");
		return listaDatos;
	}

}
