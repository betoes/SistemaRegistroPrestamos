package domain;

/**
 *
 * @author Ã�ngel SÃ¡nchez
 */
public class Hardware {

  private String numeroSerie;
  private String tipo;
  private String modelo;
  private String numeroInventario;
  private String estado;
  private String descripcion;
  
  public Hardware() {
    
  }

  public Hardware(String numeroSerie, String tipo, String modelo, String numeroInventario, 
      String estado, String descripcion) {
    this.numeroSerie = numeroSerie;
    this.tipo = tipo;
    this.modelo = modelo;
    this.numeroInventario = numeroInventario;
    this.estado = estado;
    this.descripcion = descripcion;
  }

  public String getNumeroSerie() {
    return numeroSerie;
  }

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNumeroInventario() {
		return numeroInventario;
	}

	public void setNumeroInventario(String numeroInventario) {
		this.numeroInventario = numeroInventario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
