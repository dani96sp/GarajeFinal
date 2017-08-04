package com.everis.alicante.courses.becajava.garage.interfaces.implementation;


import java.io.IOException;

import org.apache.log4j.Logger;

import com.everis.alicante.courses.becajava.garage.domain.GarajeException;
import com.everis.alicante.courses.becajava.garage.interfaces.GarajeExceptionDAO;

public class GarajeExceptionDAOImpl implements GarajeExceptionDAO {
	static Logger log = Logger.getLogger(GarajeExceptionDAOImpl.class);

	@Override
	public void create(GarajeException ex) throws IOException  {
			log.error(ex);
			
			
			
		}

		
	}

