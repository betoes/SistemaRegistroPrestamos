package domain;

import java.util.Date;

public class Dictamen {
  private String idDictamen;
  private Date fechaDictamen;
  private String tipo;
  private String observacion;
  private String descripcion;

  public Dictamen(String idDictamen, Date fechaDictamen, String tipo, String observacion,
      String descripcion) {
    this.idDictamen = idDictamen;
    this.fechaDictamen = fechaDictamen;
    this.tipo = tipo;
    this.observacion = observacion;
    this.descripcion = descripcion;
  }

  public Date getFechaDictamen() {
    return fechaDictamen;
  }

  public void setFechaDictamen(Date fechaDictamen) {
    this.fechaDictamen = fechaDictamen;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public String getObservacion() {
    return observacion;
  }

  public void setObservacion(String observacion) {
    this.observacion = observacion;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getIdDictamen() {
    return idDictamen;
  }

  public void setIdDictamen(String idDictamen) {
    this.idDictamen = idDictamen;
  }

}
