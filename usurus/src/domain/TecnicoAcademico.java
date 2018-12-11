package domain;

/**
 * Clase tecnico.
 * 
 * @author Jethran Gomez
 *
 */
public class TecnicoAcademico {
  private String nombre;
  private String noPersonal;
  private String telefono;
  private String correoElectronico;
  private String entidadAcademica;


  /**
   * Constructo tecnico.
   * 
   * @param noPersonal numero personal del tecnico
   * @param nombre nombe del personal
   * @param telefono telefono del personal
   * @param correoElectronico correo electronico del personal
   * @param entidadAcademica entidad academica de donde pertenece el personal
   */
  public TecnicoAcademico(String noPersonal, String nombre, String telefono,
      String correoElectronico, String entidadAcademica) {
    this.nombre = nombre;
    this.noPersonal = noPersonal;
    this.telefono = telefono;
    this.correoElectronico = correoElectronico;
    this.entidadAcademica = entidadAcademica;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getNoPersonal() {
    return noPersonal;
  }

  public void setNoPersonal(String noPersonal) {
    this.noPersonal = noPersonal;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getCorreoElectronico() {
    return correoElectronico;
  }

  public void setCorreoElectronico(String correoElectronico) {
    this.correoElectronico = correoElectronico;
  }

  public String getEntidadAcademica() {
    return entidadAcademica;
  }

  public void setEntidadAcademica(String entidadAcademica) {
    this.entidadAcademica = entidadAcademica;
  }



}
