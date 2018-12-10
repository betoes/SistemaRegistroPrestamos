package dao;

import java.util.List;
import domain.Software;

public interface ISoftwareDao {

  List<Software> obtenerSoftwares();

  Software obtenerSoftware(String id);

  String obtenerLicenciaSoftware(String softwareId);

  boolean agregarSoftware(Software software, String idLicencia);

  boolean modificarSoftware(Software software, String idLicencia);

  boolean eliminarSoftware(String id);

  boolean existe(String id);

  List<Software> obtenerSoftwarePorNombre(String nombre);

}
