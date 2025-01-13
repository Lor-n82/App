package com.example.App.bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.example.App.model.Usuario;
import com.example.App.utils.DebugUtils;

public class Consultas {

	ConexionBBDD cn = new ConexionBBDD();
	Connection conexion = null;
	DebugUtils debug = new DebugUtils();

	public int registrarUsuario(String usuario, String password) {
		int exito = 0;

		try {
			conexion = cn.crearConexion();
			// Crear la sentencia SQL de inserción
			String insercionSQL = "INSERT INTO Usuarios (nombre, password) VALUES (?, ?)";

			// Preparar la sentencia
			PreparedStatement preparedStatement = conexion.prepareStatement(insercionSQL);
			preparedStatement.setString(1, usuario); // Valor para columna1
			preparedStatement.setString(2, password); // Valor para columna2

			// Ejecutar la inserción
			exito = preparedStatement.executeUpdate();
			debug.info("Usuario " + usuario + " registrado");
		} catch (SQLException e) {
			debug.error(e.getMessage());
		} finally {
			cn.cerrarConexion(conexion);
		}

		return exito;

	}

	public Usuario consultaUsuario(String usuario, String password) {

		Usuario usu = null;
		
		try {
			// Establecer la conexión
			conexion = cn.crearConexion();

			Statement statement = conexion.createStatement();

			// Realizar la consulta
			String consulta = "SELECT * FROM Usuarios where ";
			consulta += "nombre='" + usuario + "' and password='" + password +"'";
			debug.info(consulta);
			ResultSet resultSet = statement.executeQuery(consulta);

			// Mostrar los resultados
			while (resultSet.next()) {
				usu = new Usuario();
				usu.setId(resultSet.getInt("id"));;
				usu.setNombre(resultSet.getString("nombre"));
				usu.setPassword(resultSet.getString("password"));
			}
			
			if(usu.getNombre()!=null && !usu.getNombre().equalsIgnoreCase("")){
				debug.info(usu.getNombre() + " existe");
			}
			// Cerrar la conexión
			resultSet.close();
			statement.close();
		} catch (Exception e) {
			debug.error(e.getMessage());
		} finally {
			cn.cerrarConexion(conexion);
		}
		return usu;
	}

	public int eliminarUsuario(int id) {
		int exito = 0;
		try {
			conexion = cn.crearConexion();
			// Comando para eliminar el registro
			String eliminarRegistro = "DELETE FROM nombre_de_la_tabla WHERE id = ?";
			PreparedStatement preparedStatement = conexion.prepareStatement(eliminarRegistro);
			preparedStatement.setInt(1, id);

			exito = preparedStatement.executeUpdate();

			// Cerrar la conexión
			preparedStatement.close();
			conexion.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.cerrarConexion(conexion);
		}
		return exito;
	}
	
	public int registroFichaje(String idUsuario, String estado, int solicitado) {
		
		int exito = 0;

		try {
			conexion = cn.crearConexion();
			// Crear la sentencia SQL de inserción
			String insercionSQL = "INSERT INTO Fichaje (idUsuario, fechaEntrada, fechaSalida, solicitado) VALUES (?, ?, ?, ?)";

			// Preparar la sentencia
			PreparedStatement preparedStatement = conexion.prepareStatement(insercionSQL);
			preparedStatement.setInt(1, Integer.valueOf(idUsuario)); 
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			if(estado.equalsIgnoreCase("entrada")) {
				preparedStatement.setTimestamp(2, timestamp); 
				preparedStatement.setTimestamp(3, null); 
			}else {
				preparedStatement.setTimestamp(2, null); 
				preparedStatement.setTimestamp(3, timestamp); 
			}
			
			preparedStatement.setInt(4, solicitado); 

			// Ejecutar la inserción
			exito = preparedStatement.executeUpdate();
			debug.info("Fecha de " + estado + " grabada");
		} catch (SQLException e) {
			debug.error(e.getMessage());
		} finally {
			cn.cerrarConexion(conexion);
		}

		return exito;

		
	}
	
}
