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
import domain.Dictamen;

public class DictamenDao implements IDictamenDao {

  private Dictamen dictamen;
  private List<Dictamen> listaDictamen;
  private String query;
  private Connection connection;
  private String idDictamen;
  private Date fechaDictamen;
  private String tipo;
  private String observacion;
  private String descripcion;

  /**
   * Metodo para recupera las licencias de la base de datos.
   */
  @Override
  public List<Dictamen> obtenerListaDictamen() {

    listaDictamen = new ArrayList<>();
    query = "Select * from dictamen";
    connection = DataBase.getDataBaseConnection();

    try {
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet result = statement.executeQuery();

      while (result.next()) {
        idDictamen = result.getString("idDictamen");
        fechaDictamen = result.getDate("fechaDictamen");
        tipo = result.getString("tipo");
        observacion = result.getString("observacion");
        descripcion = result.getString("descripcion");

        dictamen = new Dictamen(idDictamen, fechaDictamen, tipo, observacion, descripcion);
        listaDictamen.add(dictamen);
      }

    } catch (SQLException ex) {
      Logger.getLogger(DictamenDao.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      DataBase.closeConnection();
    }

    return listaDictamen;
  }

  @Override
  public List<Dictamen> obtenerDictamen(String id) {
    listaDictamen = new ArrayList<>();
    query = "select * from dictamen where nombre LIKE ?";

    connection = DataBase.getDataBaseConnection();
    try {
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, "%" + id + "%");

      ResultSet result = statement.executeQuery();
      while (result.next()) {
        idDictamen = result.getString("idDictamen");
        fechaDictamen = result.getDate("fechaDictamen");
        tipo = result.getString("tipo");
        observacion = result.getString("observacion");
        descripcion = result.getString("descripcion");

        dictamen = new Dictamen(idDictamen, fechaDictamen, tipo, observacion, descripcion);
        listaDictamen.add(dictamen);

      }

    } catch (SQLException ex) {
      Logger.getLogger(DictamenDao.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      DataBase.closeConnection();
    }

    return listaDictamen;
  }

  /**
   * Metodo para agregar una software a la base de datos.
   * 
   * @param software objeto de tipo Software y idLicencia de tipo String
   * @return agregado un boolean que asigna true o false si se agregó con exito
   */
  @Override
  public boolean registrarDictamen(Dictamen dictamen, String idHardware, String idTecnico) {

    boolean agregado = false;
    query = "insert into dictamen values (?, ?, ?, ?, ?, ?, ?)";
    connection = DataBase.getDataBaseConnection();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    try {
      PreparedStatement statement = connection.prepareStatement(query);

      String date = format.format(dictamen.getFechaDictamen());

      statement.setString(1, dictamen.getIdDictamen());
      statement.setDate(2, java.sql.Date.valueOf(date));
      statement.setString(3, dictamen.getTipo());
      statement.setString(4, dictamen.getObservacion());
      statement.setString(5, dictamen.getDescripcion());
      statement.setString(6, idHardware);
      statement.setString(7, idTecnico);

      statement.executeUpdate();
      agregado = true;
    } catch (SQLException ex) {
      Logger.getLogger(DictamenDao.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      DataBase.closeConnection();
    }
    return agregado;
  }

  /**
   * metodo para eliminar una software de la base de datos.
   * 
   * @param id de la licencia a eliminar
   */
  @Override
  public boolean eliminarDictamen(String id) {

    boolean borrado = false;
    query = "Delete from dictmaen where idDictamen = ?";
    connection = DataBase.getDataBaseConnection();

    try {
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, id);

      statement.executeUpdate();

      borrado = true;
    } catch (SQLException ex) {
      Logger.getLogger(SoftwareDao.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      DataBase.closeConnection();
    }

    return borrado;
  }
}
