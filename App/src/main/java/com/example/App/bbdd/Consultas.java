package com.example.App.bbdd;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.example.App.model.Usuario;
import com.example.App.utils.DebugUtils;

public class Consultas {

	ConexionBBDD cn = new ConexionBBDD();
	Connection conexion = null;
	DebugUtils debug = new DebugUtils();

	/**
	 * Registra un usuario
	 * @param usuario
	 * @param password
	 * @return
	 */
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

	/**
	 * Consulta si el usuario ya esta grabado
	 * @param usuario
	 * @param password
	 * @return
	 */
	public Usuario consultaUsuario(String usuario, String password) {

		debug.info("Entramos en consultaUsuario con parametros usuario=" + usuario + " password=" + password);
		Usuario usu = null;
		
		try {
			// Establecer la conexión
			conexion = cn.crearConexion();

			Statement statement = conexion.createStatement();

			// Realizar la consulta
			String consulta = "SELECT * FROM Usuarios where ";
			consulta += "nombre='" + usuario + "' and password='" + password +"'";
			debug.info("SQL :: " + consulta);
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
		debug.info("Salimos del metodo consultaUsuario");
		return usu;
	}

	/**
	 * Elimina a un usuario por su id
	 * @param id
	 * @return
	 */
	public int eliminarUsuario(int id) {
		debug.info("Entramos al metodo eliminarUsuario con parametro id=" + id);
		int exito = 0;
		try {
			conexion = cn.crearConexion();
			// Comando para eliminar el registro
			String eliminarRegistro = "DELETE FROM nombre_de_la_tabla WHERE id = ?";
			debug.info("SQL ::" + eliminarRegistro);
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
		debug.info("Salimos del metodo eliminarUsuario con exito=" + exito);
		return exito;
	}
	
	public int eliminarRegistroSolicitadoAnterior(String id, String solicitado, String fechaElegida, String switchFecha) {
	    debug.info("Entramos al metodo eliminarRegistroSolicitadoAnterior con parametro id=" + id + ", solicitado=" + solicitado + ", fechaElegida=" + fechaElegida);
	    int exito = 0;
	    Connection conexion = null;

	    try {
	        conexion = cn.crearConexion();

	        // Comando para eliminar el registro
	        String eliminarRegistro = "DELETE FROM Fichaje WHERE idUsuario = ? and solicitado = ?";

	        if (switchFecha != null && switchFecha.equalsIgnoreCase("Entrada")) {
	            eliminarRegistro += " and DATE(fechaEntrada) = ?";
	        } else {
	            eliminarRegistro += " and DATE(fechaSalida) = ?";
	        }

	        debug.info("SQL ::" + eliminarRegistro);

	        PreparedStatement preparedStatement = conexion.prepareStatement(eliminarRegistro);

	        preparedStatement.setInt(1, Integer.parseInt(id));
	        preparedStatement.setInt(2, Integer.parseInt(solicitado));

	        debug.info("La fecha de modificacion es: " + fechaElegida);

	        // Convertir la fecha a formato yyyy-MM-dd
	        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
	        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
	        Date parsedDate = inputFormat.parse(fechaElegida);
	        String fechaConvertida = outputFormat.format(parsedDate);

	        preparedStatement.setString(3, fechaConvertida);

	        exito = preparedStatement.executeUpdate();

	        // Cerrar la conexión
	        preparedStatement.close();
	        conexion.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (conexion != null) {
	            try {
	                cn.cerrarConexion(conexion);
	            } catch (Exception e) {
	                debug.error(e.getMessage());
	            }
	        }
	    }

	    debug.info("Salimos del metodo eliminarRegistroSolicitadoAnterior con exito=" + exito);
		return exito;
	}

	
	
	/**
	 * Registro de horas para fichar
	 * @param idUsuario
	 * @param estado / Entrada - Salida
	 * @param solicitado / 0 no, 1 si
	 * @param fecha 
	 * @return
	 */
	public int registroFichaje(String idUsuario, String estado, String solicitado, String fechaModificada) {
		debug.info("Entramos al metodo registroFichaje con parametro idusuario=" + idUsuario + " estado=" + estado + 
				   " solicitado=" + solicitado + " fechaModificacion=" + fechaModificada);
		int exito = 0;
		boolean existe = comprobarFecha(idUsuario, estado, solicitado, fechaModificada);
		
		try {
			if(!existe || solicitado.equalsIgnoreCase("1")) {
				conexion = cn.crearConexion();
				// Crear la sentencia SQL de inserción
				String insercionSQL = "INSERT INTO Fichaje (idUsuario, fechaEntrada, fechaSalida, solicitado) VALUES (?, ?, ?, ?)";
				debug.info("SQL :: " + insercionSQL);
				// Preparar la sentencia
				PreparedStatement preparedStatement = conexion.prepareStatement(insercionSQL);
				preparedStatement.setInt(1, Integer.valueOf(idUsuario));
				Timestamp timestamp = null;
				if(fechaModificada!=null && !fechaModificada.equalsIgnoreCase("")) {
					debug.info("La fecha de modificacion es: " + fechaModificada);
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					java.util.Date parsedDate = dateFormat.parse(fechaModificada); 
				    timestamp = new Timestamp(parsedDate.getTime());
				}else {
					timestamp = new Timestamp(System.currentTimeMillis());
					
				}
				
				if(estado.equalsIgnoreCase("entrada")) {
					preparedStatement.setTimestamp(2, timestamp); 
					preparedStatement.setTimestamp(3, null); 
				}else {
					preparedStatement.setTimestamp(2, null); 
					preparedStatement.setTimestamp(3, timestamp); 
				}
				
				preparedStatement.setInt(4, Integer.valueOf(solicitado)); 
	
				// Ejecutar la inserción
				exito = preparedStatement.executeUpdate();
				debug.info("Fecha de " + estado + " grabada");
			}else {
				debug.info("La fecha de " + estado + " ya esta registrada a dia de hoy");
			}
		} catch (SQLException e) {
			debug.error(e.getMessage());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			debug.error(e.getMessage());
		} finally {
			cn.cerrarConexion(conexion);
		}
		debug.info("Salimos del metodo registroFichaje con exito= " + exito);
		return exito;

		
	}
	
	/**
	 * Comprueba si la fecha esta grabada para el dia actual
	 * @param idUsuario
	 * @param fecha / Entrada - Salida
	 * @param fecha2 
	 * @param solicitado 
	 * @return
	 */
	public boolean comprobarFecha(String idUsuario, String fecha, String solicitado, String fechaModificacion) {
		
		debug.info("Entramos en conprobarFecha con parametros idUsuario=" + idUsuario + " fecha=" + fecha + " solicitado=" + solicitado + " fechaModificacion=" + fechaModificacion);
		
		boolean existe = false;
		
		try {
			// Establecer la conexión
			conexion = cn.crearConexion();

			Statement statement = conexion.createStatement();

			// Realizar la consulta
			String consulta = "SELECT * FROM Fichaje where ";
			if(fecha!=null  && fecha.equalsIgnoreCase("Entrada")) {
				consulta += "fechaEntrada is not null and DATE(fechaEntrada) = CURDATE() and idUsuario='" + idUsuario + "'";
			}else if(fecha!=null  && fecha.equalsIgnoreCase("Salida")) {
				consulta += "fechaSalida is not null and DATE(fechaSalida) = CURDATE() and idUsuario='" + idUsuario + "'";
			}
			
			debug.info("SQL :: " + consulta);
			ResultSet resultSet = statement.executeQuery(consulta);

			// Mostrar los resultados
			while (resultSet.next()) {
				existe = true;
				debug.info("La fecha de " + fecha + " ya existe");
			}
			// Cerrar la conexión
			resultSet.close();
			statement.close();
		} catch (Exception e) {
			debug.error(e.getMessage());
		} finally {
			cn.cerrarConexion(conexion);
		}
		String fechaExiste = existe?"Existe":"No existe";
		debug.info("Salimos del metodo comprobarFecha :: la fecha " + fecha + " " + fechaExiste);
		
		return existe;
		
	}
	
	public int registroHistoricoFichaje(String idUsuario, String estado, String solicitado, String fecha) {
		debug.info("Entramos al metodo registroHistoricoFichaje con parametro idusuario=" + idUsuario + " estado=" + estado + 
				   " solicitado=" + solicitado + " fecha=" + fecha);
		int exito = 0;
		
		try {
			
			conexion = cn.crearConexion();
			// Crear la sentencia SQL de inserción
			String insercionSQL = "INSERT INTO Historico (idUsuario, fecha, tipo, solicitud) VALUES (?, ?, ?, ?)";
			debug.info("SQL :: " + insercionSQL);
			// Preparar la sentencia
			PreparedStatement preparedStatement = conexion.prepareStatement(insercionSQL);
			preparedStatement.setInt(1, Integer.valueOf(idUsuario));
			Timestamp timestamp = null;
			if(fecha!=null && !fecha.equalsIgnoreCase("")) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				java.util.Date parsedDate = dateFormat.parse(fecha); 
			    timestamp = new Timestamp(parsedDate.getTime());
			}else {
				timestamp = new Timestamp(System.currentTimeMillis());
				
			}
		    
			if(estado.equalsIgnoreCase("entrada")) {
				preparedStatement.setTimestamp(2, timestamp); 
				preparedStatement.setString(3, "Entrada");
			}else {
				preparedStatement.setTimestamp(3, timestamp); 
				preparedStatement.setString(3, "Salida");
			}
			
			preparedStatement.setInt(4, Integer.valueOf(solicitado));

			// Ejecutar la inserción
			exito = preparedStatement.executeUpdate();
			debug.info("Fecha de " + estado + " grabada en el historico");
			
		} catch (SQLException e) {
			debug.error(e.getMessage());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			debug.error(e.getMessage());
		} finally {
			cn.cerrarConexion(conexion);
		}
		debug.info("Salimos del metodo registroHistoricoFichaje con exito= " + exito);
		return exito;

		
	}
	
}
