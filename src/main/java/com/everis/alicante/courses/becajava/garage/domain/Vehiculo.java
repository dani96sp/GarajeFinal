package com.everis.alicante.courses.becajava.garage.domain;

public class Vehiculo {
	
	private String matricula;

	private String tipoVehiculo;
	
	private int idTipoVehiculo;


	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public String getTipoVehiculo() {
		return tipoVehiculo;
	}


	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	
	
	public int getIdTipoVehiculo() {
		return idTipoVehiculo;
	}


	public void setIdTipoVehiculo(int idTipoVehiculo) {
		this.idTipoVehiculo = idTipoVehiculo;
	}

	
	public String convierteAFormatoTxt(){
		
		String str="";
		
		str=str.concat(String.valueOf(this.matricula));
		str=str.concat(";");
		str=str.concat(String.valueOf(this.tipoVehiculo));	
	
		
		return str;
	}
	

}
