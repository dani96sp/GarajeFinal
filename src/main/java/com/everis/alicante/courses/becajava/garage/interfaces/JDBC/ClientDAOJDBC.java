package com.everis.alicante.courses.becajava.garage.interfaces.JDBC;

import java.io.IOException;
import java.util.Map;

import com.everis.alicante.courses.becajava.garage.domain.Cliente;
import java.sql.*;

public interface ClientDAOJDBC {
	
	void create(Cliente cliente) throws IOException;
	 
	 Cliente read(String nif) throws IOException;
	 
	 void update(Cliente cliente) throws IOException;
	 
	 void delete(String nif) throws IOException;
	 
	 Connection getConnection() throws IOException;

	 Map<String,Cliente> readAll() throws IOException;

}
