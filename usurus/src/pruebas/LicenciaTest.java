package pruebas;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import domain.Licencia;

class LicenciaTest {

  static Date fechaI = new Date();
  static Date fechaF = new Date();

  private static Licencia licencia = new Licencia("01", 23, fechaI, fechaF, "HPP3-RT4Y-134R-1234",
      "Microsoft", "academico", "FFM");

  private static Licencia licenciaG =
      new Licencia("01", 23, fechaI, fechaF, "HPP3RT4Y134R1234", "Microsoft", "academico", "FFM");


  @BeforeClass
  public static void setUp() {

    System.out.println("Iniciando pruebas de Licencia");
  }

  @Before
  public void preparar() {
    System.out.println("Ejecutando una prueba");
  }

  @AfterClass
  public static void oneTimeTearDown() {
    System.out.println("Pruebas finalizadas de la clase cuadrado");
  }

  @After
  public void tearDown() {
    System.out.println("Se finalizó una prueba");
  }

  @Test
  public void regresaClaveGuionTest() {
    String resultadoEsperado = "HPP3-RT4Y-134R-1234";
    Assert.assertEquals(licencia.claveGuion(licenciaG.getClave()), resultadoEsperado);
  }

  @Test
  public void regresaClaveSinGuionTest() {
    String resultadoEsperado = "HPP3RT4Y134R1234";
    Assert.assertEquals(licencia.claveSinGuion(licencia.getClave()), resultadoEsperado);
  }
}
