package com.example.App.bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBBDD {
	
	public Connection crearConexion() {
		
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		}
		
		String url = "jdbc:mysql://mysql-36e49c6f-loren-f8fc.g.aivencloud.com:21788/defaultdb?ssl-mode=REQUIRED";
		String usuario = "avnadmin";
		String contraseña = "AVNS_5vRaHXoRDOCrASLVWoy";
		
		Connection conexion = null;

		try {
		    conexion = DriverManager.getConnection(url, usuario, contraseña);
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		
		return conexion;
	}
	
	public void cerrarConexion(Connection conexion) {
		try {
		    if (conexion != null && !conexion.isClosed()) {
		        conexion.close();
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	}
}
