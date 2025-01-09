package com.example.App.bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.App.model.Usuario;

public class Consultas {

	ConexionBBDD cn = new ConexionBBDD();
	Connection conexion = null;

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

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cn.cerrarConexion(conexion);
		}

		return exito;

	}

	public int crearTabla() {

		int exito = 0;
		try {
			conexion = cn.crearConexion();

			// Crear la sentencia SQL de creación de la tabla
			String creacionTablaSQL = "CREATE TABLE Usuarios (" + "id INT PRIMARY KEY AUTO_INCREMENT, "
					+ "nombre VARCHAR(100), " + "password VARCHAR(100))";

			// Crear el Statement
			Statement statement = conexion.createStatement();

			// Ejecutar la creación de la tabla
			exito = statement.executeUpdate(creacionTablaSQL);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cn.cerrarConexion(conexion);
		}
		return exito;

	}

	public List<Usuario> consultaUsuarios() {

		List<Usuario> usuarios = new ArrayList();

		try {
			// Establecer la conexión
			conexion = cn.crearConexion();

			Statement statement = conexion.createStatement();

			// Realizar la consulta
			String consulta = "SELECT * FROM Usuarios";
			ResultSet resultSet = statement.executeQuery(consulta);

			// Mostrar los resultados
			while (resultSet.next()) {
				Usuario usu = new Usuario();
				usu.setNombre(resultSet.getString("nombre"));
				usu.setPassword(resultSet.getString("password"));
				usuarios.add(usu);
			}

			// Cerrar la conexión
			resultSet.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.cerrarConexion(conexion);
		}
		return usuarios;
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

}
