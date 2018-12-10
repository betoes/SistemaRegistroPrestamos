package dao;

import java.util.List;
import domain.Software;

public interface ISoftwareDao {

  List<Software> obtenerSoftwares();

  Software obtenerSoftware(String id);

  boolean agregarSoftware(Software software, String idLicencia);

  boolean modificarSoftware(Software software);

  boolean eliminarSoftware(String id);

  boolean existe(String id);

}
