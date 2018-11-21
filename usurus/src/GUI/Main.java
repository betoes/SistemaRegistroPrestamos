package GUI;

import dao.HardwareDao;
import domain.Hardware;

public class Main {
  
  public static void main(String[] args) {
    Hardware nuevo = new Hardware("ijefbiabd", "ividudijd", "idjbd", "ijdfidf", "ijdcducb", "isjdbodf");
    HardwareDao hardwaredb = new HardwareDao();
    
    hardwaredb.registrarHardware(nuevo);
  }
}
