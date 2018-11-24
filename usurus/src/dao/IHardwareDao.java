package dao;

import domain.Hardware;

public interface IHardwareDao {
  boolean registrarHardware(Hardware nuevo);

  void verHardware(Hardware hw);

  void eliminarHardware();

  void modificarHardware();
}
