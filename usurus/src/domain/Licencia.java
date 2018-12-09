
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



}
