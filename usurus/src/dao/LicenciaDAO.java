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

public class LicenciaDAO implements ILicenciaDAO {

  private List<Licencia> listaLicencias;
  private String query;
  private Connection connection;
  private Licencia licencia;
  private Date fechaInicio;
  private Date fechaFin;
  private int numeroLicencias;
  private String idLicencia;
  private String clave;
  private String proveedor;
  private String caracter;
  private String tipoLicenciamiento;

  public LicenciaDAO() {

  }

  @Override
  public List<Licencia> obtenerLicencias() {

    listaLicencias = new ArrayList<>();
    query = "Select * from licencia";
    connection = DataBase.getDataBaseConnection();

    try {
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet result = statement.executeQuery();

      while (result.next()) {

        idLicencia = result.getString("idLicencia");
        numeroLicencias = result.getInt("numeroLicencias");
        fechaInicio = result.getDate("fechaInicio");
        fechaFin = result.getDate("fechaFin");
        clave = result.getString("clave");
        proveedor = result.getString("proveedor");
        caracter = result.getString("caracter");
        tipoLicenciamiento = result.getString("tipoLicenciamiento");

        licencia = new Licencia(idLicencia, numeroLicencias, fechaInicio, fechaFin, clave,
            proveedor, caracter, tipoLicenciamiento);

        listaLicencias.add(licencia);
      }

    } catch (SQLException ex) {
      Logger.getLogger(LicenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      DataBase.closeConnection();
    }

    return listaLicencias;
  }

  @Override
  public Licencia obtenerLicencia(String id) {

    query = "Select * from licencia where idLicencia = ?";
    connection = DataBase.getDataBaseConnection();

    try {
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(0, id);

      ResultSet result = statement.executeQuery();
      result.next();

      numeroLicencias = result.getInt("numeroLicencias");
      fechaInicio = result.getDate("fechaInicio");
      fechaFin = result.getDate("fechaFin");
      clave = result.getString("clave");
      proveedor = result.getString("proveedor");
      caracter = result.getString("caracter");
      tipoLicenciamiento = result.getString("tipoLicenciamiento");


      licencia = new Licencia(id, numeroLicencias, fechaInicio, fechaFin, clave, proveedor,
          caracter, tipoLicenciamiento);


    } catch (SQLException ex) {
      Logger.getLogger(LicenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      DataBase.closeConnection();
    }

    return licencia;
  }

  @Override
  public boolean agregarLicencia(Licencia licencia) {

    boolean agregado = true;
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
      agregado = true;
    } catch (SQLException ex) {
      Logger.getLogger(LicenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      DataBase.closeConnection();
    }
    return agregado;
  }

  @Override
  public boolean modficarLicencia(Licencia licencia) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean eliminarLicencia(int idLicencia) {
    // TODO Auto-generated method stub
    return false;
  }

}
