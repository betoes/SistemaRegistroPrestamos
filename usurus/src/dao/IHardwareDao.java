package dao;

import domain.Hardware;

public interface IHardwareDao {
  boolean registrarHardware(Hardware nuevo);
  
  void verSoftware(Hardware hw);
  
  void eliminarSoftware();
  
  void modificarSoftware();
}