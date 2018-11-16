package dao;

import domain.Hardware;

public interface IHardwareDao {
  public boolean registrarHardware(Hardware nuevo);
  
  public void verSoftware(Hardware hw);
  
  public void eliminarSoftware();
  
  public void modificarSoftware();
}