package com.everis.alicante.courses.becajava.garage.interfaces.JDBC;

import java.io.IOException;
import java.util.List;

import com.everis.alicante.courses.becajava.garage.domain.Vehiculo;
import java.sql.*;

public interface VehiculoDAOJDBC {
	
	void create(Vehiculo vehiculo) throws IOException;
	 
	 Vehiculo read(String matricula) throws IOException;
	 
	 void update(Vehiculo vehiculo) throws IOException;
	 
	 void delete(String matricula) throws IOException;
	 
	 Connection getConnection() throws IOException;

	 List<Vehiculo> readAll() throws IOException;
	 	
}
