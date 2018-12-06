package dao;

import java.util.List;

import domain.Hardware;

public interface IHardwareDao {
  
  List<Hardware> obtenerHardware(); 

  Hardware obtenerHardware(String noInventario); //ya

  boolean registrarHardware(Hardware nuevo); //ya

  boolean eliminarHardware(String noInventario);

  boolean modificarHardware(Hardware hardware);
}
