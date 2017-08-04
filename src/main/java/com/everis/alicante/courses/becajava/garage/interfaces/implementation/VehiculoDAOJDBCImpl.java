package com.everis.alicante.courses.becajava.garage.interfaces.implementation;

import java.io.*;
import java.sql.*;
import java.util.*;

import com.everis.alicante.courses.becajava.garage.domain.Vehiculo;
import com.everis.alicante.courses.becajava.garage.interfaces.JDBC.VehiculoDAOJDBC;

public class VehiculoDAOJDBCImpl implements VehiculoDAOJDBC {

	private final String MYSQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final String JDBC_CADENA_CONEXION = "jdbc:mysql://localhost:3306/garaje";
	private final String JDBC_USR = "root";
	private final String JDBC_PWD = "";
	
	@Override
	public void create(Vehiculo vehiculo) throws IOException {
		String sql = "INSERT INTO VEHICULOS (MATRICULA, TIPO_VEHICULO) VALUES ( ?, ?)";

		Connection cn = null;
		
		try {
			
			cn = this.getConnection();
			PreparedStatement pst = cn.prepareStatement(sql);
			
			pst.setString(1, vehiculo.getMatricula());
			pst.setInt(2, vehiculo.getIdTipoVehiculo());
//			String sql = " INSERT INTO VEHICULOS (MATRICULA, TIPO_VEHICULO) VALUES ('" + vehiculo.getMatricula() + "','" + vehiculo.getIdTipoVehiculo() + "')";
			pst.executeQuery();
		}catch (Exception e) {
			System.out.println("Error al insertar el Vehiculo " + e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@Override
	public Connection getConnection() throws IOException {
		
		Connection cn = null;
		
		try {
			// Carga del driver
			Class.forName(MYSQL_JDBC_DRIVER);
			
			// Conexion a la BBDD
			cn = DriverManager.getConnection(JDBC_CADENA_CONEXION,JDBC_USR,JDBC_PWD);		
		} catch (Exception e) {
			System.out.println("Error al obtener la conexion");
		}
		return cn;
	}
	
	
	@Override
	public List<Vehiculo> readAll() throws IOException {
			
		List<Vehiculo> vehiculos= new ArrayList<>();		 
		

		Connection cn = null;
		Statement st = null;
		Vehiculo vehiculo = null;

		try {
			
			cn = this.getConnection();
			st = cn.createStatement();
			String sql = "SELECT * FROM vehiculos, tipos_vehiculo WHERE vehiculos.TIPO_VEHICULO = tipos_vehiculo.ID_TIPO";
			ResultSet rs = st.executeQuery(sql);

			
			
			while(rs.next()) {
				vehiculo = new Vehiculo();
				vehiculo.setIdTipoVehiculo(rs.getInt("TIPO_VEHICULO"));
				vehiculo.setMatricula(rs.getString("MATRICULA"));
				vehiculo.setTipoVehiculo(rs.getString("NOMBRE_TIPO"));			
				
				vehiculos.add(vehiculo);
				
			}
			
		}catch (Exception e) {
			System.out.println("Error al buscar el vehiculo  " + e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	
		 	  	
		return  vehiculos;
	}


	@Override
	public Vehiculo read(String matricula) throws IOException {
		 
		Connection cn = null;
		PreparedStatement pst = null;
		Vehiculo vehiculo = new Vehiculo();

		try {
			
			cn = this.getConnection();
			String sql = "SELECT * FROM vehiculos, tipos_vehiculo WHERE vehiculos.TIPO_VEHICULO = tipos_vehiculo.ID_TIPO AND MATRICULA = ?";
			pst = cn.prepareStatement(sql);
			pst.setString(1, matricula);
			// ERROR
			
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				vehiculo.setIdTipoVehiculo(rs.getInt("TIPO_VEHICULO"));
				vehiculo.setMatricula(rs.getString("MATRICULA"));
				vehiculo.setTipoVehiculo(rs.getString("NOMBRE_TIPO"));
				
			}
			
		}catch (Exception e) {
			System.out.println("Error al buscar el vehiculo  " + e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
		
		return vehiculo;
	}

	
	@Override
	public void update(Vehiculo vehiculo) throws IOException {
		// TODO Auto-generated method stub
		
	}
	


	@Override
	public void delete(String matricula) throws IOException {
		Connection cn = null;
		PreparedStatement pst = null;
		
		try {
			
			cn = this.getConnection();
			
			
			String sql = "DELETE FROM vehiculos WHERE MATRICULA = ?";
			pst = cn.prepareStatement(sql);
			pst.setString(1, matricula);
			
			int result = pst.executeUpdate();
			if (result !=0) {
				System.out.println("Se ha borrado el vehiculo con la matricula "+ matricula + " correctamente.");
			} else if (result == 0) {
				System.out.println("No se ha encontrado la matricula");
			} else {
				System.out.println("Ha ocurrido algo extraño con la consulta a BBDD");
			}
			
			
		}catch (Exception e) {
			System.out.println("Error al borrar el Vehiculo " + e.getMessage());
		} finally {
			try {
				cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}


}
