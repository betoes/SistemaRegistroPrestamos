
package domain;

import java.util.Date;

/**
 * Clase licencia.
 * 
 * @author Jethran Gomez.
 * @version 1.0
 */
public class Licencia {
  private String idLicencia;
  private int numeroLicencias;
  private Date fechaInicio;
  private Date fechaFin;
  private String clave;
  private String proveedor;
  private String caracter;
  private String tipoLicenciamiento;
  private static final int PARTES = 4;

  /**
   * Constructor de licencia.
   * 
   * @param idLicencia id de la licencia
   * @param numeroLicencias numero de licencias
   * @param fechaInicio fecha agregada de la licencia
   * @param fechaFin fecha de expirqacion de la licencia
   * @param clave clave de la licencia
   * @param proveedor proveedor de la licencia ligado al software o hardware
   * @param caracter carcter de la licencia
   * @param tipoLicenciamiento tipo de licencia
   */
  public Licencia(String idLicencia, int numeroLicencias, Date fechaInicio, Date fechaFin,
      String clave, String proveedor, String caracter, String tipoLicenciamiento) {
    this.idLicencia = idLicencia;
    this.numeroLicencias = numeroLicencias;
    this.fechaInicio = fechaInicio;
    this.fechaFin = fechaFin;
    this.clave = clave;
    this.proveedor = proveedor;
    this.caracter = caracter;
    this.tipoLicenciamiento = tipoLicenciamiento;
  }

  public String getIdLicencia() {
    return idLicencia;
  }

  public void setIdLicencia(String idLicencia) {
    this.idLicencia = idLicencia;
  }

  public int getNumeroLicencias() {
    return numeroLicencias;
  }

  public void setNumeroLicencias(int numeroLicencias) {
    this.numeroLicencias = numeroLicencias;
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

  public String getClave() {
    return clave;
  }

  public void setClave(String clave) {
    this.clave = clave;
  }

  public String getProveedor() {
    return proveedor;
  }

  public void setProveedor(String proveedor) {
    this.proveedor = proveedor;
  }

  public String getCaracter() {
    return caracter;
  }

  public void setCaracter(String caracter) {
    this.caracter = caracter;
  }

  public String getTipoLicenciamiento() {
    return tipoLicenciamiento;
  }

  public void setTipoLicenciamiento(String tipoLicenciamiento) {
    this.tipoLicenciamiento = tipoLicenciamiento;
  }

  /**
   * Metodo para agregarle guiones a la clave
   * 
   * @param clave
   * @return cadenaGuion regresa la clave con guiones
   */
  public String claveGuion(String c) {

    String cadena = c;
    char[] caracterCadena = cadena.toCharArray();
    String cadenaGuion = "";

    for (int x = 0; x < caracterCadena.length; x++) {
      cadenaGuion += caracterCadena[x];
      if (x == 3 || x == 7 || x == 11) {
        cadenaGuion += "-";
      }
    }

    return cadenaGuion;
  }

  /**
   * Metodo para regresar la clave sin guiones
   * 
   * @param clave recibe una clave con guiones
   * @return claveG regresa la clave sin guiones
   */
  public String claveSinGuion(String c) {

    String[] parts = c.split("-");
    String claveG = "";
    for (int part = 0; part < PARTES; part++) {
      claveG += parts[part];
    }

    return claveG;
  }

}
