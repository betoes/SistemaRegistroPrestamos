package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import datasource.DataBase;
import domain.Licencia;

/**
 * Clase que implementa los metodos de la interfaz ILicenciaDAO para dar el funcionamiento adecuado
 * a la conexi�n con la base de datos.
 * 
 * @author Jethran Gomez
 * @version 1.0
 */
public class LicenciaDao implements ILicenciaDao {

  private String query;
  private Connection connection;

  public LicenciaDao() {

  }

  /**
   * Metodo para recupera las licencias de la base de datos.
   */
  @Override
  public List<Licencia> obtenerLicencias() {

    List<Licencia> listaLicencias = new ArrayList<>();
    query = "Select * from licencia";
    connection = DataBase.getDataBaseConnection();

    try {
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet result = statement.executeQuery();

      while (result.next()) {

        String idLicencia = result.getString("idLicencia");
        int numeroLicencias = result.getInt("numeroLicencias");
        Date fechaInicio = result.getDate("fechaInicio");
        Date fechaFin = result.getDate("fechaFin");
        String clave = result.getString("clave");
        String proveedor = result.getString("proveedor");
        String caracter = result.getString("caracter");
        String tipoLicenciamiento = result.getString("tipoLicenciamiento");

        Licencia licencia = new Licencia(idLicencia, numeroLicencias, fechaInicio, fechaFin, clave,
            proveedor, caracter, tipoLicenciamiento);

        listaLicencias.add(licencia);
      }

    } catch (SQLException ex) {
      Logger.getLogger(LicenciaDao.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      DataBase.closeConnection();
    }

    return listaLicencias;
  }

  /**
   * Metodo para recupera una licencia de la base de datos.
   * 
   * @param id de la licencia a recuperar
   */
  @Override
  public Licencia obtenerLicencia(String id) {

    query = "Select * from licencia where idLicencia = ?";
    connection = DataBase.getDataBaseConnection();
    Licencia licencia = null;

    try {
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, id);

      ResultSet result = statement.executeQuery();
      result.next();

      int numeroLicencias = result.getInt("numeroLicencias");
      Date fechaInicio = result.getDate("fechaInicio");
      Date fechaFin = result.getDate("fechaFin");
      String clave = result.getString("clave");
      String proveedor = result.getString("proveedor");
      String caracter = result.getString("caracter");
      String tipoLicenciamiento = result.getString("tipoLicenciamiento");


      licencia = new Licencia(id, numeroLicencias, fechaInicio, fechaFin, clave, proveedor,
          caracter, tipoLicenciamiento);


    } catch (SQLException ex) {
      Logger.getLogger(LicenciaDao.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      DataBase.closeConnection();
    }

    return licencia;
  }

  /**
   * Metodo para recuperar licencia de la base de datos.
   * 
   * @return listaLicencia regresa una lista con todas las licencias de la BD
   * 
   */
  public List<String> obtenerIdLicencia() {
    List<String> listaIdLicencia = new ArrayList<>();
    query = "Select idLicencia from licencia";
    connection = DataBase.getDataBaseConnection();

    try {
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet result = statement.executeQuery();

      while (result.next()) {
        listaIdLicencia.add(result.getString("idLicencia"));
      }

    } catch (SQLException ex) {
      Logger.getLogger(LicenciaDao.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      DataBase.closeConnection();
    }

    return listaIdLicencia;
  }

  /**
   * Metodo para agregar una licencia a la base de datos.
   * 
   * @param licencia objeto de tipo Licencia
   */
  @Override
  public void agregarLicencia(Licencia licencia) {

    query = "insert into licencia values (?, ?, ?, ?, ?, ?, ?, ?)";
    connection = DataBase.getDataBaseConnection();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    try {
      PreparedStatement statement = connection.prepareStatement(query);

      String dateF = format.format(licencia.getFechaFin());
      String dateI = format.format(licencia.getFechaInicio());

      statement.setString(1, licencia.getIdLicencia());
      statement.setInt(2, licencia.getNumeroLicencias());
      statement.setDate(3, java.sql.Date.valueOf(dateI));
      statement.setDate(4, java.sql.Date.valueOf(dateF));
      statement.setString(5, licencia.getClave());
      statement.setString(6, licencia.getProveedor());
      statement.setString(7, licencia.getCaracter());
      statement.setString(8, licencia.getTipoLicenciamiento());

      statement.execute();

    } catch (SQLException ex) {
      Logger.getLogger(LicenciaDao.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      DataBase.closeConnection();
    }
  }

  /**
   * Metodo para modificar una licencia de la base de datos.
   * 
   * @param licencia objeto de tipo licencia
   */
  @Override
  public void modficarLicencia(Licencia licencia) {

    query = "UPDATE licencia set numeroLicencias = ?, fechaInicio = ?, fechaFin = ?, clave = ?,"
        + " proveedor = ?, caracter = ?, " + "tipoLicenciamiento = ? where idLicencia = ?";
    connection = DataBase.getDataBaseConnection();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    try {
      PreparedStatement statement = connection.prepareStatement(query);
      String dateF = format.format(licencia.getFechaFin());
      String dateI = format.format(licencia.getFechaInicio());

      statement.setInt(1, licencia.getNumeroLicencias());
      statement.setDate(2, java.sql.Date.valueOf(dateI));
      statement.setDate(3, java.sql.Date.valueOf(dateF));
      statement.setString(4, licencia.getClave());
      statement.setString(5, licencia.getProveedor());
      statement.setString(6, licencia.getCaracter());
      statement.setString(7, licencia.getTipoLicenciamiento());
      statement.setString(8, licencia.getIdLicencia());

      statement.executeUpdate();

    } catch (SQLException ex) {
      Logger.getLogger(LicenciaDao.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      DataBase.closeConnection();
    }

  }

  /**
   * metodo para eliminar una licencia de la base de datos.
   * 
   * @param id de la licencia a eliminar
   */
  @Override
  public void eliminarLicencia(String id) {

    query = "Delete from licencia where idLicencia = ?";
    connection = DataBase.getDataBaseConnection();

    try {
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, id);

      statement.executeUpdate();

    } catch (SQLException ex) {
      Logger.getLogger(LicenciaDao.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      DataBase.closeConnection();
    }

  }

  /**
   * Metodo para verificar si existe una licencia.
   * 
   * @param id identificador de la licencia
   */
  @Override
  public boolean existe(String id) {

    boolean existe = false;
    query = "Select * from licencia where idLicencia = ?";
    connection = DataBase.getDataBaseConnection();

    try {
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, id);

      ResultSet result = statement.executeQuery();

      existe = result.next();


    } catch (SQLException ex) {
      Logger.getLogger(LicenciaDao.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      DataBase.closeConnection();
    }

    return existe;
  }

  /**
   * metodo para verficar si existe licencia en hardware.
   * 
   * @param id identificador de la licencia
   */
  @Override
  public boolean existeHardware(String id) {

    boolean existe = false;
    query = "Select * from hardware where Licencia_idLicencia = ?";
    connection = DataBase.getDataBaseConnection();

    try {
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, id);

      ResultSet result = statement.executeQuery();

      existe = result.next();


    } catch (SQLException ex) {
      Logger.getLogger(LicenciaDao.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      DataBase.closeConnection();
    }

    return existe;
  }

  /**
   * metodo para verficar si existe licencia en software.
   * 
   * @param id identificador de la licencia
   */
  @Override
  public boolean existeSoftware(String id) {

    boolean existe = false;
    query = "Select * from software where Licencia_idLicencia = ?";
    connection = DataBase.getDataBaseConnection();

    try {
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, id);

      ResultSet result = statement.executeQuery();

      existe = result.next();


    } catch (SQLException ex) {
      Logger.getLogger(LicenciaDao.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      DataBase.closeConnection();
    }

    return existe;
  }
}
