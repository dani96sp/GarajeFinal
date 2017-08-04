package com.everis.alicante.courses.becajava.garage.interfaces.implementation;

import java.io.*;
import java.sql.*;
import java.util.*;

import com.everis.alicante.courses.becajava.garage.domain.Cliente;
import com.everis.alicante.courses.becajava.garage.interfaces.JDBC.ClientDAOJDBC;

public class ClienteDAOJDBCImpl implements ClientDAOJDBC {

	private final String MYSQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final String JDBC_CADENA_CONEXION = "jdbc:mysql://localhost:3306/garaje";
//	private final String JDBC_USR = "root";
//	private final String JDBC_PWD = "root";
	
	@Override
	public void create(Cliente cliente) throws IOException {
		Connection cn = null;
		Statement st = null;
		
		try {
			
			cn = this.getConnection();
			st = cn.createStatement();
			
			String sql = " INSERT INTO CLIENTES "
					+ " VALUES ('" + cliente.getNif() + "','" + cliente.getNombreCompleto() + "','"
					+ cliente.getVehiculo() + "')";
			st.execute(sql);
		}catch (Exception e) {
			System.out.println("Error al insertar el cliente " + e.getMessage());
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
			cn = DriverManager.getConnection(JDBC_CADENA_CONEXION);		
		} catch (Exception e) {
			System.out.println("Error al obtener la conexion");
		}
		return cn;
	}
	
	
	@Override
	public Map<String,Cliente> readAll() throws IOException {
			
		Map<String,Cliente> clientes;
		
		
		clientes= new TreeMap<String,Cliente>();		 
			
		String linea;
			 
		File file= new File("src/main/resources/Clientes.txt");
		FileReader reader= new FileReader(file);
		BufferedReader  buffer= new BufferedReader(reader);
			 		 
		while((linea=buffer.readLine())!=null){				  
				
			if(!linea.contains("NIF")||linea.isEmpty()){
					
				Cliente clienteTemp= new Cliente();	
					
				String[] temp= linea.split(";");
					
				clienteTemp.setNif(temp[0]);
				clienteTemp.setNombreCompleto(temp[1]);											
				clientes.put(clienteTemp.getNif(),clienteTemp);
				
			}
				
		 }
				 
		 reader.close();				 
	
		return  clientes;	
	}


	@Override
	public Cliente read(String nif) throws IOException {
		 
		 Cliente clienteTemp=null;			
		 String linea;
		 
		 File file= new File("src/main/resources/Clientes.txt");
		 FileReader reader= new FileReader(file);
		 BufferedReader  buffer= new BufferedReader(reader);
		 		 
		 while((linea=buffer.readLine())!=null){				  
			
			if(!linea.contains("NIF")||linea.isEmpty()){
			
				String[] temp= linea.split(";");
			
				if(nif.equals(temp[0])){
										
					clienteTemp= new Cliente();						
					clienteTemp.setNif(temp[0]);
					clienteTemp.setNombreCompleto(temp[1]);			
					
				}
						
			}
			
		 }
			 
		 reader.close();		
		 	  	
		return  clienteTemp;	
	}

	
	@Override
	public void update(Cliente cliente) throws IOException {
		// TODO Auto-generated method stub
		
	}
	


	@Override
	public void delete(String nif) throws IOException {
		// TODO Auto-generated method stub
		
	}


}
