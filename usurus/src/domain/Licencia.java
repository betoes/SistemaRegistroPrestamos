
package domain;

import java.util.Date;

public class Licencia {
  private Date fechaInicio;
  private Date fechaFin;
  private int numeroLicencias;
  private String idLicencia;
  private String clave;
  private String proveedor;
  private String caracter;
  private String tipoLicenciamiento;


  public Licencia() {

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
