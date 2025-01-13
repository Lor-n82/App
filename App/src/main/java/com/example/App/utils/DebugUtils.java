package com.example.App.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DebugUtils {
	
	 protected static final Logger logger = LogManager.getLogger();
	 
	 public void error(String texto) {
		 logger.error(texto);
		 
	 }
	 
	 public void warn(String texto) {
		 logger.warn(texto);
	 }

	 public void info(String texto) {
		 logger.info(texto);
	 }

	 public void debug(String texto) {
		 logger.debug(texto);
	 }

}
