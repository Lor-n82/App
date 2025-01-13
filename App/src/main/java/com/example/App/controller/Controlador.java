package com.example.App.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.App.bbdd.Consultas;
import com.example.App.model.Modelo;
import com.example.App.utils.DebugUtils;

@Controller
public class Controlador {
	
	private ModelAndView model;
	DebugUtils debug = new DebugUtils();
	Consultas consulta;
	
	@RequestMapping("/")
	public ModelAndView inicio() {
		
		model = new ModelAndView();
		model.setViewName("index");
		return model;
		
	}
	
	@RequestMapping("/principal")
	public ModelAndView pantallaPrincipal(@RequestParam(name="username", required=false) String usuario,@RequestParam(name="password", required=false) String password,
			@RequestParam(name="login", required=false) String login,@RequestParam(name="registro", required=false) String registro) {
		debug.info("Entra en principal");
		HashMap<String, String> datos = Modelo.logicaLoginRegistro(usuario, password, login);
		String mensaje = datos.get("mensaje")!=null?datos.get("mensaje"):"";
		String vista = datos.get("vista")!=null?datos.get("vista"):"";
		String idUsuario = datos.get("idUsuario")!=null?datos.get("idUsuario"):"";
		model = new ModelAndView();
		if(idUsuario!=null && !idUsuario.equalsIgnoreCase("")) {
			model.addObject("idUsuario", idUsuario);
		}
		model.addObject("mensaje", mensaje);
		model.setViewName(vista);
		return model;
	}

	@RequestMapping("/accionPrincipalFichaje")
	public ModelAndView accionPrincipalFichaje(@RequestParam(name="fichar") String estadoFichaje, @RequestParam(name="idUsuario") String idUsuario) {
		consulta = new Consultas();
		debug.info(idUsuario + " : " +  estadoFichaje);
		int exito = consulta.registroFichaje(idUsuario, estadoFichaje, 0);
		debug.info(""+exito);
		if(exito==1) {
			if(estadoFichaje.equalsIgnoreCase("Salida")) {
				estadoFichaje = null;
			}else {
				estadoFichaje = "Salida";
			}
		}
		model = new ModelAndView();
		model.addObject("estadoFichaje", estadoFichaje);
		model.addObject("idUsuario", idUsuario);
		model.setViewName("principal");
		return model;
		
	}
	
	@RequestMapping("/accionPrincipalModificar")
	public ModelAndView gestionPrincipalModificar() {
		
		model = new ModelAndView();
		model.setViewName("calendario");
		return model;
		
	}

}
