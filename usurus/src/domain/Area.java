package domain;

public class Area {
  String nombre;
  String tipoArea;
  String ubicacion;
  String descripcion;
  
  public Area(String nombre, String tipoArea, String ubicacion, String descripcion) {
	this.nombre = nombre;
	this.tipoArea = tipoArea;
	this.ubicacion = ubicacion;
	this.descripcion = descripcion;
  }
  
  public String getNombre() {
	return nombre;
  }
  
  public void setNombre(String nombre) {
 	this.nombre = nombre;
  }
  
  public String gettipoArea() {
	return tipoArea;
  }
  
  public void settipoArea(String tipoArea) {
 	this.tipoArea = tipoArea;
  }
  
  public String getUbicacion() {
	return ubicacion;
  }
  
  public void setUbicacion(String ubicacion) {
	this.ubicacion = ubicacion;
  }
  
  public String getDescripcion() {
	return descripcion;
  }
  
  public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
  }
  
  
  
}
