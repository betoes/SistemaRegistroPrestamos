package domain;

import java.util.Date;

public class Resguardo {
  private Date fecha;
  private String horaprestamo;
  private String horadevolucion;
  private String estado;
  
  public Resguardo(Date fecha, String horaprestamo, String horadevolucion, String estado) {
	this.fecha = fecha;
	this.horaprestamo = horaprestamo;
	this.horadevolucion = horadevolucion;
	this.estado = estado;
  }

  public Date getFecha() {
	return fecha;
  }

  public void setFecha(Date fecha) {
	this.fecha = fecha;
  }

  public String getHoraprestamo() {
	return horaprestamo;
  }

  public void setHoraprestamo(String horaprestamo) {
	this.horaprestamo = horaprestamo;
  }

  public String getHoradevolucion() {
	return horadevolucion;
  }

  public void setHoradevolucion(String horadevolucion) {
	this.horadevolucion = horadevolucion;
  } 

  public String getEstado() {
	return estado;
  }

  public void setEstado(String estado) {
	this.estado = estado;
  }
  
  
  
  
}
