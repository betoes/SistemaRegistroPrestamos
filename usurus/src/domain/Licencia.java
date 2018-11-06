package domain;

import java.util.Date;

public class Licencia {
  private long clave;
  private Date fechaInicio;
  private Date fechaFin;
  private String proveedor;
  private int numeroLicencias;
  private String caracter;
  private String responsable;
  private String tipoLicenciamiento;
  
  public Licencia(long clave, Date fechaInicio, Date fechaFin, String proveedor, int numeroLicencias, String caracter,
		String responsable, String tipoLicenciamiento) {
	this.clave = clave;
	this.fechaInicio = fechaInicio;
	this.fechaFin = fechaFin;
	this.proveedor = proveedor;
	this.numeroLicencias = numeroLicencias;
	this.caracter = caracter;
	this.responsable = responsable;
	this.tipoLicenciamiento = tipoLicenciamiento;
  }

  public long getClave() {
	return clave;
  }

  public void setClave(long clave) {
	this.clave = clave;
  }

  public Date getFechaInicio() {
	return fechaInicio;
  }

  public void setFechaInicio(Date fechaInicio) {
	this.fechaInicio = fechaInicio;
  }

  public Date getFechaFin() {
	return fechaFin;
  }

  public void setFechaFin(Date fechaFin) {
	this.fechaFin = fechaFin;
  }

  public String getProveedor() {
	return proveedor;
  }

  public void setProveedor(String proveedor) {
	this.proveedor = proveedor;
  }

  public int getNumeroLicencias() {
	return numeroLicencias;
  }

  public void setNumeroLicencias(int numeroLicencias) {
	this.numeroLicencias = numeroLicencias;
  }

  public String getCaracter() {
	return caracter;
  }

  public void setCaracter(String caracter) {
	this.caracter = caracter;
  }

  public String getResponsable() {
	return responsable;
  }

  public void setResponsable(String responsable) {
	this.responsable = responsable;
  }

  public String getTipoLicenciamiento() {
	return tipoLicenciamiento;
  }

  public void setTipoLicenciamiento(String tipoLicenciamiento) {
	this.tipoLicenciamiento = tipoLicenciamiento;
  }
  
  
  
  
  
  
  
  
}
