package com.everis.alicante.courses.becajava.garage.domain;

import java.util.Calendar;

import org.apache.log4j.Logger;


public class GarajeException extends Exception{
	static Logger log = Logger.getLogger(GarajeException.class);


	private static final long serialVersionUID = -8890393005500715068L;
	
	public Exception excepcionOrigen;
	
	public void gestionaExcepcion() {

		log.error("Ha ocurrido una excepcion del tipo :" + this.excepcionOrigen.getClass()
		+ "y por este motivo: " + this.excepcionOrigen.getLocalizedMessage());
		
	}
	

	public GarajeException(Exception e) {
		this.excepcionOrigen=e;
	}


	public String adaptaFormatoTxt(){
		
		String tmp="";
		tmp=tmp.concat(Calendar.getInstance().getTime().toString());
		tmp=tmp.concat(";");
		tmp=tmp.concat(this.excepcionOrigen.getClass().getSimpleName());
		tmp=tmp.concat(";");
		tmp=tmp.concat(this.excepcionOrigen.getLocalizedMessage());
		
		return tmp;
		
	}



}
