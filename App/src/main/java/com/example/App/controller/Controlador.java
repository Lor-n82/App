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
		debug.info("Entra en /");
		model = new ModelAndView();
		model.setViewName("index");
		debug.info("Vamos a la vista index");
		return model;
		
	}
	
	@RequestMapping("/principal")
	public ModelAndView pantallaPrincipal(@RequestParam(name="usuario", required=false) String usuario,@RequestParam(name="password", required=false) String password,
			@RequestParam(name="login", required=false) String login,@RequestParam(name="registro", required=false) String registro) {
		
		debug.info("Entra en principal con parametros: usuario=" + usuario + " password=" + password + " login=" + login + " registro=" + registro);
		HashMap<String, String> datos = Modelo.logicaLoginRegistro(usuario, password, login);
		String mensaje = datos.get("mensaje")!=null?datos.get("mensaje"):"";
		String vista = datos.get("vista")!=null?datos.get("vista"):"";
		String idUsuario = datos.get("idUsuario")!=null?datos.get("idUsuario"):"";
		model = new ModelAndView();
		if(idUsuario!=null && !idUsuario.equalsIgnoreCase("")) {
			model.addObject("idUsuario", idUsuario);
		}
		model.addObject("usuario", usuario);
		model.addObject("password", password);
		model.addObject("mensaje", mensaje);
		model.setViewName(vista);
		debug.info("Vamos a la vista " + vista);
		return model;
	}
	
	@RequestMapping("/menuAcciones")
	public ModelAndView menuAcciones(@RequestParam(name="idUsuario", required=false) String idUsuario, @RequestParam(name="usuario", required=false) String usuario,@RequestParam(name="password", required=false) String password,
			@RequestParam(name="login", required=false) String login,@RequestParam(name="registro", required=false) String registro) {
		
		debug.info("Entra en menuAcciones con parametros: idUsuario=" + idUsuario + " usuario=" + usuario + " password=" + password + " login=" + login + " registro=" + registro);
		model = new ModelAndView();
		model.addObject("idUsuario", idUsuario);
		model.addObject("usuario", usuario);
		model.addObject("password", password);
		model.setViewName("principal");
		debug.info("Vamos a la vista principal");
		return model;
	}

	@RequestMapping("/accionPrincipalFichaje")
	public ModelAndView accionPrincipalFichaje(@RequestParam(name="fichar") String estadoFichaje, @RequestParam(name="idUsuario") String idUsuario) {
		debug.info("Entra en accionPrincipalFichaje con parametros: fichar=" + estadoFichaje + " idusuario=" + idUsuario);
		consulta = new Consultas();
		model = new ModelAndView();
		String mensaje = null;
		int exito = consulta.registroFichaje(idUsuario, estadoFichaje, "0", "");
		if(exito==1) {
			consulta.registroHistoricoFichaje(idUsuario, estadoFichaje, "0", "");
			if(estadoFichaje.equalsIgnoreCase("Salida")) {
				estadoFichaje = null;
			}else {
				estadoFichaje = "Salida"; 
			}
		}else {
			mensaje = "Ya has registrado la entrada hoy, pulsa modificar para solicitar una corrección";
		}
		if(mensaje!=null) {
			model.addObject("mensaje", mensaje);;
		}
		model.addObject("estadoFichaje", estadoFichaje);
		model.addObject("idUsuario", idUsuario);
		model.setViewName("principal");
		debug.info("Vamos a la vista principal");
		return model;
		
	}
	
	@RequestMapping("/accionPrincipalModificar")
	public ModelAndView accionPrincipalModificar(@RequestParam(name="usuario") String usuario,@RequestParam(name="password") String password, @RequestParam(name="idUsuario") String idUsuario, @RequestParam(name="modificar") String solicitado) {
		debug.info("Entra en accionPrincipalModificar con parametros: idusuario=" + idUsuario + " modificar=" + solicitado);
		consulta = new Consultas();
		model = new ModelAndView();
		model.addObject("usuario", usuario);
		model.addObject("password", password);
		model.addObject("idUsuario", idUsuario);
		model.addObject("solicitado", solicitado);
		model.setViewName("calendario");
		debug.info("Vamos a la vista calendario");
		return model;
		
	}
	
	@RequestMapping("/modificarFechaSeleccionada")
	public ModelAndView modificarFechaSeleccionada(@RequestParam(name="datetimepicker") String fecha, @RequestParam(name="switchFecha") String switchFecha, @RequestParam(name="idUsuario") String idUsuario, @RequestParam(name="solicitado") String solicitado) {
		debug.info("Entra en modificarFechaSeleccionada con parametros: fecha=" + fecha + " switchFecha=" + switchFecha + " idUsuario=" + idUsuario + " solicitado=" + solicitado);
		consulta = new Consultas();
		int exito = consulta.eliminarRegistroSolicitadoAnterior(idUsuario, solicitado, fecha, switchFecha);
		if(exito==1) {
			debug.info("Solicitud Anterior eliminada");
		}
		exito = consulta.registroFichaje(idUsuario, switchFecha, solicitado, fecha);
		if(exito==1) {
			consulta.registroHistoricoFichaje(idUsuario, switchFecha, solicitado, fecha);
			debug.info("Solicitud realizada");
		}
		
		model = new ModelAndView();
		model.addObject("idUsuario", idUsuario);
		model.addObject("solicitado", solicitado);
		model.setViewName("calendario");
		return model;
		
	}
	

}
