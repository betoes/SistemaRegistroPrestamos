package tests.folder;

import dao.HardwareDao;
import domain.Hardware;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class HardwareDaoTest {

  private static HardwareDao prueba = new HardwareDao();
  private static Hardware nuevo;
  
  @BeforeClass
  public static void setUp() {
    System.out.println("Esta es la clase que prueba los méodos de la clase HardwareDao");
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
    System.out.println("Se realizaron todas las pruebas");
  }

  @Test
  public void testRegistrar() {
    nuevo = new Hardware("1234567890","CPU","dc5850 Small","N00123401","Resguardo","");
    boolean expected = true;
    boolean actual = prueba.registrarHardware(nuevo);
    
    Assert.assertEquals(expected, actual);
    
  }

}
