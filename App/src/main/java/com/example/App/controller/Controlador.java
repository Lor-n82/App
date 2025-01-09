package com.example.App.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.App.model.Modelo;

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
	public ModelAndView pantallaPrincipal() {
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
