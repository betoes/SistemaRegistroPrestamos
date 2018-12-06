package dao;

import dao.HardwareDao;
import datasource.DataBase;
import domain.Hardware;
import domain.Licencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1> HardwareDao </h1>
 * Clase que implementa los metodos de la interfaz IHardwareDao para dar el funcionamiento
 * adecuado a la conexión con la base de datos.
 * 
 * @author Ángel Sánchez
 * @version 1.0
 * @since 2018/10/23
 */
public class HardwareDao implements IHardwareDao {
  private Connection connection;
  private String query;
  ArrayList<Hardware> listaHardware = new ArrayList<Hardware>();
  Hardware hardware;
  private String numeroSerie;
  private String tipo;
  private String modelo;
  private String numeroInventario;
  private String estado;
  private String descripcion;

  public HardwareDao() {

  }  

  /**
   * Se regresan todos los registros de hardware en base de datos.
   * @return Objeto con todos los resultados de hardware encontrados
   */
  @Override
  public List<Hardware> obtenerHardware() {
    listaHardware = new ArrayList<Hardware>();
    query = "SELECT * FROM hardware";
    connection = DataBase.getDataBaseConnection();
    
    try {
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet result = statement.executeQuery();
      
      while (result.next()) {

        numeroSerie = result.getString("serie");
        tipo = result.getString("numeroLicencias");
        descripcion = result.getString("fechaInicio");
        estado = result.getString("fechaFin");
        modelo = result.getString("clave");
        numeroInventario = result.getString("proveedor");
        
        hardware = new Hardware(numeroSerie, tipo, modelo, numeroInventario, estado, descripcion);

        listaHardware.add(hardware);
      }
    } catch (Exception ex) {
      Logger.getLogger(HardwareDao.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      DataBase.closeConnection();
    }
    
    return listaHardware;
  }
  
  /**
   * Devuelve un solo registro de hardware, basandose en el parámetro recibido.
   * @param noInventario Número de inventario que tiene asignado el hardware en la base de datos
   * @return Objeto encontrado en la base de datos
   */
  @Override
  public Hardware obtenerHardware(String noInventario) {
    connection = DataBase.getDataBaseConnection();
    query = "SELECT * FROM hardware WHERE numeroInventario = ?";

    try {
      PreparedStatement statement = connection.prepareStatement(query);
      
      statement.setString(1, noInventario);

      ResultSet result = statement.executeQuery();
      result.next();
      
      String numeroSerie = result.getString("serie");
      String tipo = result.getString("tipo");
      String modelo = result.getString("modelo");
      String numeroInventario = result.getString("numeroInventario");
      String estado = result.getString("estado");
      String descripcion = result.getString("descripcion");
      
      hardware = new Hardware(numeroSerie, tipo, modelo, numeroInventario, estado, descripcion);
      
      return hardware;
      
    } catch (SQLException ex) {
      Logger.getLogger(HardwareDao.class.getName()).log(Level.SEVERE, "Error en la consulta", ex);
    } finally {
      DataBase.closeConnection();
    }
    return hardware;
  }
  
  /**
   * Guarda un nuevo registro de hardware en la base de datos.
   * @param nuevo objeto que se desea registrar
   * @return true en caso de guardar correctamente en base de datos
   */
  @Override
  public boolean registrarHardware(Hardware nuevo) {
    query = "INSERT INTO hardware VALUES (?,?,?,?,?,?)";
    connection = DataBase.getDataBaseConnection();

    try {
      PreparedStatement statement = connection.prepareStatement(query);
      
      statement.setString(1, nuevo.getNumeroSerie());
      statement.setString(2, nuevo.getTipo());
      statement.setString(3, nuevo.getDescripcion());
      statement.setString(4, nuevo.getEstado());
      statement.setString(5, nuevo.getModelo());
      statement.setString(6, nuevo.getNumeroInventario());

      statement.executeUpdate();
      
    } catch (SQLException ex) {
      Logger.getLogger(Hardware.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      DataBase.closeConnection();
    }

    return false;
  }

  /**
   * Borra un registro en la base de datos, basandose en el parámetro recibido.
   * @param noInventario Se usa para encontrar el hardware que se desea eliminar
   * @return true en caso de realizar exitósamente la eliminación del registro de hardware
   */
  @Override
  public boolean eliminarHardware(String noInventario) {
    query = "SELECT FROM hardware where numeroInventario = ?";
    connection = DataBase.getDataBaseConnection();
    
    try {
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, noInventario);
      
      statement.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(HardwareDao.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      DataBase.closeConnection();
    }
    
    return false;
  }
  
  /**
   * Modifica la información de algún hardware existente en la base de datos.
   * @param hardware Objeto para modificar su información
   * @return true en caso de actualizar los datos del softare corréctamente
   */
  @Override
  public boolean modificarHardware(Hardware hardware) {
    query = "UPDATE hardware set serie = ?, tipo = ?, descripcion = ?, estado = ?, modelo = ?"
        + "nuneroInventario";
    connection = DataBase.getDataBaseConnection();
     
    try {
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, hardware.getNumeroSerie());
      statement.setString(2, hardware.getTipo());
      statement.setString(3, hardware.getDescripcion());
      statement.setString(4, hardware.getEstado());
      statement.setString(5, hardware.getModelo());
      statement.setString(6, hardware.getNumeroInventario());
      
      statement.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(HardwareDao.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      DataBase.closeConnection();
    }
    
    return false;
  }
}
