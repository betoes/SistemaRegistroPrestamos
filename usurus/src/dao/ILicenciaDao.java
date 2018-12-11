package dao;

import java.util.List;
import domain.Licencia;

public interface ILicenciaDao {

  List<Licencia> obtenerLicencias();

  Licencia obtenerLicencia(String id);

  void agregarLicencia(Licencia licencia);

  void modficarLicencia(Licencia licencia);

  void eliminarLicencia(String id);

  boolean existe(String id);

  boolean existeHardware(String id);

  boolean existeSoftware(String id);

}
