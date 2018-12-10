package domain;

public class Software {
  private String idSoftware;
  private String nombre;
  private String version;
  private double monto;
  private String marca;
  private String idioma;
  private String descripcion;
  private String origen;
  private String tipo;


  public Software(String idSoftware, String nombre, String version, double monto, String marca,
      String idioma, String descripcion, String origen, String tipo) {
    this.idSoftware = idSoftware;
    this.nombre = nombre;
    this.version = version;
    this.monto = monto;
    this.marca = marca;
    this.idioma = idioma;
    this.descripcion = descripcion;
    this.origen = origen;
    this.tipo = tipo;
  }

  public String getIdSoftware() {
    return idSoftware;
  }

  public void setIdSoftware(String idSoftware) {
    this.idSoftware = idSoftware;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public double getMonto() {
    return monto;
  }

  public void setMonto(double monto) {
    this.monto = monto;
  }

  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  public String getIdioma() {
    return idioma;
  }

  public void setIdioma(String idioma) {
    this.idioma = idioma;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getOrigen() {
    return origen;
  }

  public void setOrigen(String origen) {
    this.origen = origen;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }



}
