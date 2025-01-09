package com.example.App.controller;

import java.sql.Connection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.App.bbdd.ConexionBBDD;
import com.example.App.bbdd.Consultas;
import com.example.App.model.Modelo;
import com.example.App.model.Usuario;

@Controller
public class Controlador {
	
	private ModelAndView model;
	private Modelo modelo;
	
	@RequestMapping("/")
	public ModelAndView inicio() {
		
		model = new ModelAndView();
		model.setViewName("index");
		return model;
		
	}
	
	@RequestMapping("/principal")
	public ModelAndView pantallaPrincipal(@RequestParam(name="username", required=false) String usuario,@RequestParam(name="password", required=false) String password) {
		
		if(usuario!=null && !usuario.equalsIgnoreCase("")) {
			Consultas consulta = new Consultas();
			for (Usuario usu : consulta.consultaUsuarios()) {
				System.out.println(usu.getNombre() + " : " + usu.getPassword());
			}
		}
		
		model = new ModelAndView();
		model.setViewName("principal");
		return model;
	}
	
	@RequestMapping("/accionPrincipalFichaje")
	public ModelAndView gestionPrincipalFichaje() {
		
		model = new ModelAndView();
		model.setViewName("index");
		return model;
		
	}
	
	@RequestMapping("/accionPrincipalModificar")
	public ModelAndView gestionPrincipalModificar() {
		
		model = new ModelAndView();
		model.setViewName("calendario");
		return model;
		
	}

}
