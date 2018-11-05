package domain;

/**
 *
 * @author Ángel Sánchez
 */
public class Hardware {

  private int numeroSerie;
  private String tipo;
  private String modelo;
  private int numeroInventario;
  private String estado;
  private String descripcion;

  public Hardware(int numeroSerie, String tipo, String modelo, int numeroInventario, String estado,
          String descripcion) {
    this.numeroSerie = numeroSerie;
    this.tipo = tipo;
    this.modelo = modelo;
    this.numeroInventario = numeroInventario;
    this.estado = estado;
    this.descripcion = descripcion;
  }

  public void agregarHardware(Hardware nuevo) {
    
  }

  public void verRegistro() {

  }

  public void modificarRegistro() {

  }

  public void eliminarRegistro() {

  }

  private boolean validarRegistro() {

    return false;
  }
}
