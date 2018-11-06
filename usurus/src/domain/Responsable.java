package domain;

public class Responsable {
  private String numeroPersonal;
  private String correoEletronico;
  private String entidadAcademica;
  private String nombre;
  private String telefono;
  
  public Responsable(String numeroPersonal, String correoEletronico, String entidadAcademica, String nombre,
		String telefono) {
	this.numeroPersonal = numeroPersonal;
	this.correoEletronico = correoEletronico;
	this.entidadAcademica = entidadAcademica;
	this.nombre = nombre;
	this.telefono = telefono;
  }
  
  public String getNumeroPersonal() {
	return numeroPersonal;
  }
  
  public void setNumeroPersonal(String numeroPersonal) {
	this.numeroPersonal = numeroPersonal;
  }
  
  public String getCorreoEletronico() {
	return correoEletronico;
  }
  
  public void setCorreoEletronico(String correoEletronico) {
	this.correoEletronico = correoEletronico;
  }
  
  public String getEntidadAcademica() {
	return entidadAcademica;
  }
  
  public void setEntidadAcademica(String entidadAcademica) {
	this.entidadAcademica = entidadAcademica;
  }
  
  public String getNombre() {
	return nombre;
  }
  
  public void setNombre(String nombre) {
	this.nombre = nombre;
  }
  
  public String getTelefono() {
	return telefono;
  }
  
  public void setTelefono(String telefono) {
	this.telefono = telefono;
  }
  
}
